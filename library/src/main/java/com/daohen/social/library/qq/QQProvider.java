package com.daohen.social.library.qq;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Contexts;
import com.daohen.personal.toolbox.library.util.Toasts;
import com.daohen.social.library.qq.listener.LoginIUiListener;
import com.daohen.social.library.qq.listener.LoginListener;
import com.daohen.social.library.qq.listener.UserInfoIUiListener;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
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

    public static final QQProvider get(){
        return gDefault.get();
    }

    public void registerQQ(Context context, String appid){
        this.context = context;
        this.appid = appid;
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

    public void shareQQ(){
        Bundle bundle = new Bundle();

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
