package com.daohen.social.library.qq.listener;

import com.daohen.social.library.qq.bean.LoginResponse;
import com.tencent.tauth.IUiListener;

import org.json.JSONObject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:53
 */
public abstract class LoginIUiListener implements IUiListener {

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject = (JSONObject) o;
//        LoginResponse response =
    }

    abstract void onComplete(LoginResponse response);

}
