package com.office.test;

import org.junit.Test;

import com.office.common.util.WechatUtils;

public class HttpTest
{
    @Test
    public void testGetRequest(){
       String accessToken=WechatUtils.getAccessToken();
       System.out.println(accessToken);
    }

}
