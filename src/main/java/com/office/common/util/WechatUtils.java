package com.office.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.Element;

import org.w3c.dom.Document;

import com.alibaba.fastjson.JSONObject;
import com.office.common.constant.UrlConstant;

/**
 * 微信相关工具类
 * 
 * @author Administrator 2017-6-5 15:32:25
 */
public class WechatUtils
{
    /**
     * 获取Access_token
     * 
     * @return
     */
    public static String getAccessToken()
    {
        String url =
            MessageFormat.format(UrlConstant.GET_ACCESS_TOKEN,
                PropertiesUtils.getProperty("wechat.appid"),
                PropertiesUtils.getProperty("wechat.appsecret"));
        String resultInfo = HttpUtils.doGet(url);
        JSONObject json = JSONObject.parseObject(resultInfo);
        return json.getString("access_token");
    }

}
