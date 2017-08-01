package com.daohen.social.wb.library.api;

import com.daohen.social.wb.library.bean.WbUserInfoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/01 15:43
 */

public interface WbService {

    @GET("/users/show")
    Observable<WbUserInfoResponse> getUserInfo(@Query("access_token") String token, @Query("uid") String uid);

}
