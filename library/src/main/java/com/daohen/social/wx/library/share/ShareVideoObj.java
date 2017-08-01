package com.daohen.social.wx.library.share;

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
public class ShareVideoObj extends ShareObj {

    private ShareVideoObj(SendMessageToWX.Req req) {
        super(req);
    }

    public static class Builder{
        private String url;
        private String title;
        private String desc;
        private Bitmap thumb;
        private boolean isTimeline;

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder desc(String desc){
            this.desc = desc;
            return this;
        }

        public Builder thumb(Bitmap thumb){
            this.thumb = thumb;
            return this;
        }

        public Builder isTimeline(boolean isTimeline){
            this.isTimeline = isTimeline;
            return this;
        }

        public ShareVideoObj build(){
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
            req.transaction = buildTransation("video");
            req.message = mediaMessage;
            req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

            return new ShareVideoObj(req);
        }
    }

}
