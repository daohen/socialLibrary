package com.daohen.social.library.qq.listener;

import com.daohen.social.library.qq.bean.UserInfoResponse;
import com.daohen.thirdparty.library.gson.GsonFactory;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by alun on 17/7/18.
 */

public class UserInfoIUiListener implements IUiListener {

    private LoginListener listener;

    public UserInfoIUiListener(LoginListener listener){
        this.listener = listener;
    }

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject = (JSONObject) o;
        Type type = new TypeToken<UserInfoResponse>(){}.getType();
        UserInfoResponse response = GsonFactory.getDefault().fromJson(jsonObject.toString(), type);
        listener.onSuccess(response);
    }

    @Override
    public void onError(UiError uiError) {
        listener.onFail(uiError);
    }

    @Override
    public void onCancel() {

    }
}
