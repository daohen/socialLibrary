package com.daohen.social.wb.library.callback;

import com.daohen.social.wb.library.bean.SuccessObj;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2017/08/01 15:04
 */

public abstract class LoginAuthListener {

    private boolean isObtainUserInfo = false;

    public abstract void onSuccess(SuccessObj var1);

    public abstract void cancel();

    public abstract void onFailure();

    public boolean isObtainUserInfo() {
        return isObtainUserInfo;
    }

    public void setObtainUserInfo(boolean obtainUserInfo) {
        isObtainUserInfo = obtainUserInfo;
    }
}
