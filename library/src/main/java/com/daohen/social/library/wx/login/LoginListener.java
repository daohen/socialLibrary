package com.daohen.social.library.wx.login;

import com.daohen.social.library.wx.bean.WxUserInfoResponse;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/16 02:40
 */
public interface LoginListener {

    void onSuccess(WxUserInfoResponse response);

    void onFail(Throwable t);
}
