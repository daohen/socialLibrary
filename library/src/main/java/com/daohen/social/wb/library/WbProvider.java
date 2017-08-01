package com.daohen.social.wb.library;

import com.daohen.personal.toolbox.library.Singleton;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/01 10:49
 */

public class WbProvider {

    public static WbProvider get(){
        return gDefault.get();
    }

    private String appkey;
    private String redirectUrl;
    private String scope;

    public void init(String appkey, String redirectUrl, String scope){
        this.appkey = appkey;
        this.redirectUrl = redirectUrl;
        this.scope = scope;
    }



    private static final Singleton<WbProvider> gDefault = new Singleton<WbProvider>() {
        @Override
        protected WbProvider create() {
            return new WbProvider();
        }
    };

}
