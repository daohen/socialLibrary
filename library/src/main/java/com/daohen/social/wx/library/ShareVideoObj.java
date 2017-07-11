package com.daohen.social.wx.library;

import android.graphics.Bitmap;

import com.daohen.personal.toolbox.library.util.Bitmaps;
import com.daohen.personal.toolbox.library.util.Strings;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 14:36
 */
public class ShareVideoObj {

    private SendMessageToWX.Req req;

    private ShareVideoObj(SendMessageToWX.Req req){
        this.req = req;
    }

    protected SendMessageToWX.Req getReq(){
        return req;
    }

    protected static class Builder{
        private String url;
        private String title;
        private String desc;
        private Bitmap thumb;
        private boolean isTimeline;

        protected Builder url(String url){
            this.url = url;
            return this;
        }

        protected Builder title(String title){
            this.title = title;
            return this;
        }

        protected Builder desc(String desc){
            this.desc = desc;
            return this;
        }

        protected Builder thumb(Bitmap thumb){
            this.thumb = thumb;
            return this;
        }

        protected Builder isTimeline(boolean isTimeline){
            this.isTimeline = isTimeline;
            return this;
        }

        protected ShareVideoObj build(){
            if (!Strings.isUrl(url) || Strings.isNull(title) || thumb == null)
                throw new NullPointerException("分享的视频url、title和thumb不能为空");

            WXVideoObject videoObject = new WXVideoObject();
            videoObject.videoUrl = url;

            WXMediaMessage mediaMessage = new WXMediaMessage();
            mediaMessage.mediaObject = videoObject;
            mediaMessage.title = title;
            mediaMessage.description = desc;
            mediaMessage.thumbData = Bitmaps.bmpToByteArray(thumb, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = WxUtils.buildTransation("video");
            req.message = mediaMessage;
            req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

            return new ShareVideoObj(req);
        }
    }

}
