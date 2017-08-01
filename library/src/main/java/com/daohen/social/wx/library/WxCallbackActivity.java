package com.daohen.social.wx.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.daohen.personal.toolbox.library.util.Logs;
import com.daohen.social.wx.library.login.LoginObj;
import com.daohen.thirdparty.library.gson.GsonFactory;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;


/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 23:30
 */
public class WxCallbackActivity extends Activity implements IWXAPIEventHandler{

    private Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        WxProvider.get().handleIntent(getIntent(), this);

        gson = GsonFactory.getDefault();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        WxProvider.get().handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Logs.d(gson.toJson(baseReq));
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Logs.d(gson.toJson(baseResp));
        if (baseResp.errCode == 0){
            LoginObj.get().login(((SendAuth.Resp) baseResp).code);
        }
    }
}
