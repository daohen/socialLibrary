package com.daohen.social.wx.library.share;

import android.graphics.Bitmap;

import com.daohen.personal.toolbox.library.util.Bitmaps;
import com.daohen.personal.toolbox.library.util.Strings;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 14:09
 */
public class ShareMusicObj extends ShareObj {

    private ShareMusicObj(SendMessageToWX.Req req) {
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

        public ShareMusicObj build(){
            if (!Strings.isUrl(url) || Strings.isNull(title) || thumb == null)
                throw new NullPointerException("分享的音乐url、title和thumb不能为空");

            WXMusicObject musicObject = new WXMusicObject();
            musicObject.musicUrl = url;

            WXMediaMessage mediaMessage = new WXMediaMessage();
            mediaMessage.mediaObject = musicObject;
            mediaMessage.title = title;
            mediaMessage.description = desc;
            mediaMessage.thumbData = Bitmaps.bmpToByteArray(thumb, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransation("music");
            req.message = mediaMessage;
            req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

            return new ShareMusicObj(req);
        }
    }

}
