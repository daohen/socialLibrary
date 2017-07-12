package com.daohen.social.library.wx.share;

import android.graphics.Bitmap;

import com.daohen.personal.toolbox.library.util.Bitmaps;
import com.daohen.personal.toolbox.library.util.Strings;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 14:53
 */
public class ShareMiniProgramObj extends ShareObj {

    private ShareMiniProgramObj(SendMessageToWX.Req req) {
        super(req);
    }

    public static class Builder{
        private String url;
        private String username;
        private String path;
        private String title;
        private String desc;
        private Bitmap thumb;

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder path(String path){
            this.path = path;
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

        public ShareMiniProgramObj build(){
            if (!Strings.isUrl(url) || Strings.isNull(username) || Strings.isNull(path) || Strings.isNull(title))
                throw new NullPointerException("分享小程序时的原始url、username、path、title不能为空");

            WXMiniProgramObject miniProgramObject = new WXMiniProgramObject();
            miniProgramObject.webpageUrl = url;
            miniProgramObject.userName = username;
            miniProgramObject.path = path;

            WXMediaMessage mediaMessage = new WXMediaMessage();
            mediaMessage.mediaObject = miniProgramObject;
            mediaMessage.title = title;
            mediaMessage.description = desc;
            if (thumb != null)
                mediaMessage.thumbData = Bitmaps.bmpToByteArray(thumb, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransation("miniprogram");
            req.message = mediaMessage;
            req.scene = SendMessageToWX.Req.WXSceneSession;

            return new ShareMiniProgramObj(req);
        }
    }

}
