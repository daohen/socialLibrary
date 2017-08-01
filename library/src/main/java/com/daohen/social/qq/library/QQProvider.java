package com.daohen.social.qq.library;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Contexts;
import com.daohen.personal.toolbox.library.util.Toasts;
import com.daohen.social.qq.library.listener.LoginIUiListener;
import com.daohen.social.qq.library.listener.LoginListener;
import com.daohen.social.qq.library.listener.UserInfoIUiListener;
import com.tencent.connect.UserInfo;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 22:53
 */
public class QQProvider {

    private Tencent tencent;
    private Context context;
    private String appid;
    private String appname;

    public static final QQProvider get(){
        return gDefault.get();
    }

    public void registerQQ(Context context, String appid, String appname){
        this.context = context;
        this.appid = appid;
        this.appname = appname;
        tencent = Tencent.createInstance(appid, context);
    }

    public Tencent getTencent(){
        return tencent;
    }

    public void login(Activity activity, LoginListener listener){
        checkNull();

        if (tencent.isSessionValid()){
            Toasts.showLong("session invalid");
            return;
        }

        tencent.login(activity, "all", new LoginIUiListener(activity.getApplicationContext(), listener));
    }

    public void logout(){
        checkNull();

        tencent.logout(Contexts.getContext());
    }

    public void getUserInfo(LoginListener listener){
        checkNull();

        UserInfo userInfo = new UserInfo(context, tencent.getQQToken());
        userInfo.getUserInfo(new UserInfoIUiListener(listener));
    }

    public void shareLocalImage(Activity activity, String path, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, path);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        share(activity, bundle, listener);
    }

    public void shareDefault(Activity activity, String title, String summary, String targetUrl, String imageUrl, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        share(activity, bundle, listener);
    }

    public void shareApp(Activity activity, String title, String summary, String targetUrl, String imageUrl, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        share(activity, bundle, listener);
    }

    public void shareAudio(Activity activity, String title, String summary, String targetUrl, String imageUrl, String audioUrl, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        bundle.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, audioUrl);
        share(activity, bundle, listener);
    }

    private void share(final Activity activity, final Bundle bundle, final IUiListener listener){
        checkNull();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tencent.shareToQQ(activity, bundle, listener);
            }
        });
    }

    private static final Singleton<QQProvider> gDefault = new Singleton<QQProvider>() {
        @Override
        protected QQProvider create() {
            return new QQProvider();
        }
    };

    private void checkNull(){
        if (tencent == null)
            throw new NullPointerException("需要先注册到QQ才能使用，请先调用registerQQ");
    }

}
