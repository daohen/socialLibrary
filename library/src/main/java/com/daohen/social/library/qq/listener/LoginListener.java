package com.daohen.social.library.qq.listener;

import com.daohen.social.library.qq.bean.UserInfoResponse;
import com.tencent.tauth.UiError;

/**
 * Created by alun on 17/7/18.
 */

public interface LoginListener {

    void onSuccess(UserInfoResponse response);

    void onFail(UiError uiError);

}
