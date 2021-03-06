package com.office.wechat.controller;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.office.common.util.WechatUtils;
import com.office.wechat.form.AccessTokenDTO;
import com.office.wechat.service.WechatService;

/**
 * wechat 基础Controller
 * 
 * @author Administrator
 *
 *         2017-6-5 16:45:02
 */
@RestController
public class BaseController
{
    private static Logger logger = Logger.getLogger(BaseController.class);

    private String TOKEN = "8a551bc8";
    
    @Autowired
    private WechatService wechatService;

    /**
     * 验证请求
     * 
     * @param dto
     * @param response
     */
    @RequestMapping(value = "/app/wechat/validateRequest.do", method = {RequestMethod.GET, RequestMethod.POST })
    public void validateRequest(@ModelAttribute AccessTokenDTO dto, HttpServletRequest request,
        HttpServletResponse response,PrintWriter out)
    {

        logger.info("请求参数："+request.getQueryString());
        logger.info("验证请求【入参】" + dto.toString());
        try
        {
            boolean isGet = request.getMethod().toLowerCase().equals("get");
            if (isGet)
            {
                // 将token、timestamp、nonce三个参数进行字典序排序
                String[] params = new String[] {TOKEN, dto.getTimestamp(), dto.getNonce() };
                Arrays.sort(params);
                // 将三个参数字符串拼接成一个字符串进行sha1加密
                String clearText = params[0] + params[1] + params[2];
                String algorithm = "SHA-1";
                String sign =
                    new String(Hex.encodeHex(MessageDigest.getInstance(algorithm)
                        .digest((clearText).getBytes()),
                        true));
                // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
                if (dto.getSignature().equals(sign))
                {
                    response.getWriter().print(dto.getEchostr());
                }
            }
            else
            {
               String responseMessage= wechatService.processRequest(request);
               response.getWriter().write(responseMessage);
            }
        }
        catch (Exception e)
        {
            logger.error("验证请求【失败】" + e);
        }
    }

    /**
     * 获取AccessToken
     * 
     * @return
     */
    @RequestMapping(value = "/app/wechat/getAccessToken.do", method = RequestMethod.POST)
    public String getAccessToken()
    {
        return WechatUtils.getAccessToken();
    }

}
