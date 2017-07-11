package com.daohen.social.wx.library;

import com.daohen.personal.toolbox.library.util.Strings;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/11 12:13
 */
public class WxUtils {

    protected static String buildTransation(String type){
        return Strings.isNull(type) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}
