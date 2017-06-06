package com.office.wechat.form;

import lombok.Data;

/**
 * 请求验证DTO
 * 
 * @author Administrator
 * 
 *         2017-6-5 17:41:34
 *
 */
public @Data class AccessTokenDTO
{
    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;

}
