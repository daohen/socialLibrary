package com.daohen.social.qq.library.listener;

import com.daohen.social.qq.library.bean.UserInfoResponse;
import com.tencent.tauth.UiError;

/**
 * Created by alun on 17/7/18.
 */

public interface LoginListener {

    void onSuccess(UserInfoResponse response);

    void onFail(UiError uiError);

}
