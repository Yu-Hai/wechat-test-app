package com.office.wechat.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.office.common.util.WechatMessageUtil;
import com.office.wechat.message.InputMessage;
import com.office.wechat.service.WechatService;

@Service
public class WechatServiceImpl implements WechatService
{
    
    private static Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Override
    public String processRequest(HttpServletRequest request)
    {
        Map<String, String> map = WechatMessageUtil.xmlToMap(request);
        logger.info("请求消息："+map.toString());
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        // 消息类型
        String msgType = map.get("MsgType");
        // 默认回复一个"success"
        String responseMessage = "success";
        // 对消息进行处理
        if (WechatMessageUtil.MESSAGE_TEXT.equals(msgType)) {// 文本消息
            InputMessage message = new InputMessage();
            message.setTextMessage(map, "Hello");
            responseMessage = WechatMessageUtil.textMessageToXml(message);
        }else if(WechatMessageUtil.MESSAGE_IMAGE.equals(msgType)){
            InputMessage message = new InputMessage();
            message.setImageMessage(map);
            message.setPicUrl("http://jingyan.baidu.com/event/img/gift252-162.jpg");
            responseMessage = WechatMessageUtil.textMessageToXml(message);

        }
        logger.info("响应消息：\r\n"+responseMessage);
        return responseMessage;

    }

}
