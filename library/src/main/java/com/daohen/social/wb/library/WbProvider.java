package com.daohen.social.wb.library;

import android.app.Activity;
import android.content.Intent;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.social.wb.library.api.WbApi;
import com.daohen.social.wb.library.bean.SuccessObj;
import com.daohen.social.wb.library.callback.LoginAuthListener;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/01 10:49
 */

public class WbProvider {

    public static WbProvider get(){
        return gDefault.get();
    }

    private SsoHandler ssoHandler;
    private WbShareHandler shareHandler;

    public void initLogin(Activity activity, AuthInfo authInfo){
        WbSdk.install(activity.getApplicationContext(), authInfo);
        ssoHandler = new SsoHandler(activity);
    }

    /**
     * SSO 授权, 仅客户端
     * @param listener
     */
    public void authorizeClientSso(LoginAuthListener listener){
        checkSSoHandler();

        ssoHandler.authorizeClientSso(new WbAuthWrap(listener));
    }

    /**
     * SSO 授权, 仅Web
     * @param listener
     */
    public void authorizeWeb(LoginAuthListener listener){
        checkSSoHandler();

        ssoHandler.authorizeWeb(new WbAuthWrap(listener));
    }

    /**
     * SSO 授权, ALL IN ONE   如果手机安装了微博客户端则使用客户端授权,没有则进行网页授权
     * @param listener
     */
    public void authorize(LoginAuthListener listener){
        checkSSoHandler();

        ssoHandler.authorize(new WbAuthWrap(listener));
    }

    private class WbAuthWrap implements WbAuthListener{

        private LoginAuthListener listener;

        public WbAuthWrap(LoginAuthListener listener){
            this.listener = listener;
        }

        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            if (listener.isObtainUserInfo()){
                WbApi.get().getUserInfo(oauth2AccessToken, listener);
            } else {
                listener.onSuccess(new SuccessObj(oauth2AccessToken));
            }
        }

        @Override
        public void cancel() {
            listener.cancel();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            listener.onFailure();
        }
    }

    /**
     * SSO 授权回调
     * 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResult，调用此方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void authorizeCallBack(int requestCode, int resultCode, Intent data){
        checkSSoHandler();

        ssoHandler.authorizeCallBack(requestCode, resultCode, data);
    }


    public void initShare(Activity activity){
        shareHandler = new WbShareHandler(activity);
        shareHandler.registerApp();
    }

    public void share(WeiboMultiMessage message){
        checkShareHandler();

        shareHandler.shareMessage(message, shareHandler.isWbAppInstalled());
    }

    /**
     * onNewIntent中调用
     * @param intent
     * @param callback
     */
    public void doResultIntent(Intent intent, WbShareCallback callback){
        shareHandler.doResultIntent(intent, callback);
    }



    public static class ShareBuilder{

        private TextObject textObject;
        private ImageObject imageObject;
        private WebpageObject webpageObject;

        public ShareBuilder text(TextObject textObject){
            this.textObject = textObject;
            return this;
        }

        public ShareBuilder image(ImageObject imageObject){
            this.imageObject = imageObject;
            return this;
        }

        public ShareBuilder webpage(WebpageObject webpageObject){
            this.webpageObject = webpageObject;
            return this;
        }

        public WeiboMultiMessage build(){
            if (textObject == null && imageObject == null && webpageObject == null)
                throw new NullPointerException("没有可分享的内容");

            WeiboMultiMessage message = new WeiboMultiMessage();
            if (textObject != null)
                message.textObject = textObject;
            if (imageObject != null)
                message.imageObject = imageObject;
            if (webpageObject != null)
                message.mediaObject = webpageObject;
            return message;
        }
    }


    private void checkShareHandler(){
        if (shareHandler == null)
            throw new NullPointerException("请先调用initShare()");
    }

    private void checkSSoHandler(){
        if (ssoHandler == null)
            throw new NullPointerException("请先调用initLogin()");
    }

    private static final Singleton<WbProvider> gDefault = new Singleton<WbProvider>() {
        @Override
        protected WbProvider create() {
            return new WbProvider();
        }
    };

}
