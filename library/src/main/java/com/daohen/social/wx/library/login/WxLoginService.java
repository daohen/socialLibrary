package com.daohen.social.wx.library.login;

import com.daohen.social.wx.library.bean.AccessTokenResponse;
import com.daohen.social.wx.library.bean.CheckAccessTokenResponse;
import com.daohen.social.wx.library.bean.WxUserInfoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/12 16:13
 */
public interface WxLoginService {

    @GET("sns/oauth2/access_token")
    Observable<AccessTokenResponse> getAccessToken(@Query("appid") String appid, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grant_type);

    @GET("sns/oauth2/refresh_token")
    Observable<AccessTokenResponse> refreshToken(@Query("appid") String appid, @Query("grant_type") String grant_type, @Query("refresh_token") String refresh_token);

    @GET("sns/auth")
    Observable<CheckAccessTokenResponse> checkAccessToken(@Query("access_token") String access_token, @Query("openid") String openid);

    @GET("sns/userinfo")
    Observable<WxUserInfoResponse> getUserInfo(@Query("access_token") String access_token, @Query("openid") String openid);
}
