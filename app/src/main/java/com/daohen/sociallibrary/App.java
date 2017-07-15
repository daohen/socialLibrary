package com.daohen.sociallibrary;

import android.app.Application;

import com.daohen.social.library.wx.WxProvider;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/15 17:00
 */
public class App extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        WxProvider.get().registerWx(this, "wxa477c0daf2841dc3");
    }
}
