package com.daohen.social.wb.library;

import android.app.Activity;
import android.content.Intent;

import com.daohen.personal.toolbox.library.Singleton;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WbAuthListener;
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
    public void authorizeClientSso(WbAuthListener listener){
        checkSSoHandler();

        ssoHandler.authorizeClientSso(listener);
    }

    /**
     * SSO 授权, 仅Web
     * @param listener
     */
    public void authorizeWeb(WbAuthListener listener){
        checkSSoHandler();

        ssoHandler.authorizeWeb(listener);
    }

    /**
     * SSO 授权, ALL IN ONE   如果手机安装了微博客户端则使用客户端授权,没有则进行网页授权
     * @param listener
     */
    public void authorize(WbAuthListener listener){
        checkSSoHandler();

        ssoHandler.authorize(listener);
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
