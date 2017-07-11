package com.daohen.social.wx.library;

import android.graphics.Bitmap;

import com.daohen.personal.toolbox.library.util.Bitmaps;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 13:52
 */
public class ShareBitmapObj {

    private SendMessageToWX.Req req;

    private ShareBitmapObj(SendMessageToWX.Req req){
        this.req = req;
    }

    protected SendMessageToWX.Req getReq(){
        return req;
    }

    protected static class Builder{
        private Bitmap bitmap;
        private boolean isTimeline;

        protected Builder bitmap(Bitmap bitmap){
            this.bitmap = bitmap;
            return this;
        }

        protected Builder isTimeline(boolean isTimeline){
            this.isTimeline = isTimeline;
            return this;
        }

        protected ShareBitmapObj build(){
            if (bitmap == null)
                throw new NullPointerException("分享的图片不能为空");

            WXMediaMessage mediaMessage = new WXMediaMessage();
            mediaMessage.mediaObject = new WXImageObject(bitmap);
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            bitmap.recycle();
            mediaMessage.thumbData = Bitmaps.bmpToByteArray(thumbBmp, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = WxUtils.buildTransation("img");
            req.message = mediaMessage;
            req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

            return new ShareBitmapObj(req);
        }
    }
}
