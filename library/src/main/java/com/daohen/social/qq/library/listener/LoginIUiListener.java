package com.daohen.social.qq.library.listener;

import android.content.Context;

import com.daohen.social.qq.library.QQProvider;
import com.daohen.social.qq.library.bean.LoginResponse;
import com.daohen.thirdparty.library.gson.GsonFactory;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:53
 */
public class LoginIUiListener implements IUiListener {

    private LoginListener listener;
    private Context context;

    public LoginIUiListener(Context context, LoginListener listener){
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject = (JSONObject) o;
        Type type = new TypeToken<LoginResponse>(){}.getType();
        LoginResponse response = GsonFactory.getDefault().fromJson(jsonObject.toString(), type);
        QQProvider.get().getUserInfo(listener);
    }

    @Override
    public void onError(UiError uiError) {
        listener.onFail(uiError);
    }

    @Override
    public void onCancel() {

    }

}
