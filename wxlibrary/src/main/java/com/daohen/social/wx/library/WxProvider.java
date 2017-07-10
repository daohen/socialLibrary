package com.daohen.social.wx.library;

import android.content.Context;

import com.daohen.personal.toolbox.library.Singleton;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/10 23:56
 */
public class WxProvider {

    private static IWXAPI iwxapi;

    public static final WxProvider get(){
        return gDefault.get();
    }

    public void registerWx(Context context, String appid){
        iwxapi = WXAPIFactory.createWXAPI(context, appid, true);
        iwxapi.registerApp(appid);
    }

    private static final Singleton<WxProvider> gDefault = new Singleton<WxProvider>() {
        @Override
        protected WxProvider create() {
            return new WxProvider();
        }
    };

}
