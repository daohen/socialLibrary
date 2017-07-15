package com.daohen.social.library.wx.bean;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/12 16:19
 */
public class AccessTokenResponse {


    private String access_token;
    private long expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccessToken(){
        return access_token;
    }

    public String getOpenid(){
        return openid;
    }

    public String getUnionid(){
        return unionid;
    }

}
