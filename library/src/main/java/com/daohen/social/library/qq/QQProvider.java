package com.daohen.social.library.qq;

import android.app.Activity;
import android.content.Context;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Contexts;
import com.daohen.personal.toolbox.library.util.Toasts;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 22:53
 */
public class QQProvider {

    private Tencent tencent;

    public static final QQProvider get(){
        return gDefault.get();
    }

    public void registerQQ(Context context, String appid){
        tencent = Tencent.createInstance(appid, context);
    }

    public void login(Activity activity, IUiListener listener){
        checkNull();

        if (tencent.isSessionValid()){
            Toasts.showLong("session invalid");
            return;
        }

        tencent.login(activity, "all", listener);
    }

    public void logout(){
        checkNull();

        tencent.logout(Contexts.getContext());
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
