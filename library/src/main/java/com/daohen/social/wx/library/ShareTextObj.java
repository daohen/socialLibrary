package com.daohen.social.wx.library;

import com.daohen.personal.toolbox.library.util.Strings;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 11:28
 */
public class ShareTextObj {

    private SendMessageToWX.Req req;

    private ShareTextObj(SendMessageToWX.Req req){
        this.req = req;
    }

    protected SendMessageToWX.Req getReq(){
        return req;
    }

    protected static class Builder{

        private String content;
        private String desc;
        private boolean isTimeline;

        protected Builder content(String content){
            this.content = content;
            return this;
        }

        protected Builder desc(String desc){
            this.desc = desc;
            return this;
        }

        protected Builder isTimeline(boolean isTimeline){
            this.isTimeline = isTimeline;
            return this;
        }

        protected ShareTextObj build(){
            if (Strings.isNull(content))
                throw new NullPointerException("分享的内容不能为空");

            WXMediaMessage mediaMessage = new WXMediaMessage();
            mediaMessage.mediaObject = new WXTextObject(content);
            mediaMessage.description = desc;

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = WxUtils.buildTransation("text");
            req.message = mediaMessage;
            req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

            return new ShareTextObj(req);
        }
    }

}
