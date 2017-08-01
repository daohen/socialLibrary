package com.daohen.social.wb.library.bean;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/01 15:31
 */

public class SuccessObj {

    private Oauth2AccessToken oauth2AccessToken;
    private WbUserInfoResponse wbUserInfoResponse;


    public SuccessObj(Oauth2AccessToken oauth2AccessToken){
        this.oauth2AccessToken = oauth2AccessToken;
    }

    public SuccessObj(WbUserInfoResponse wbUserInfoResponse){
        this.wbUserInfoResponse = wbUserInfoResponse;
    }

    public Oauth2AccessToken getOauth2AccessToken() {
        return oauth2AccessToken;
    }

    public WbUserInfoResponse getWbUserInfoResponse(){
        return wbUserInfoResponse;
    }
}
