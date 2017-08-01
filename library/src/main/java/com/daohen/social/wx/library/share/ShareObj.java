package com.daohen.social.wx.library.share;

import com.daohen.personal.toolbox.library.util.Strings;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/12 15:47
 */
public class ShareObj {

    private SendMessageToWX.Req req;

    public ShareObj(SendMessageToWX.Req req){
        this.req = req;
    }

    protected static String buildTransation(String type){
        return Strings.isNull(type) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public SendMessageToWX.Req getReq(){
        return req;
    }

}
