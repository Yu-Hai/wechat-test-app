package com.office.test;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.office.common.util.WechatUtils;

public class HttpTest
{
    @Test
    public void testGetRequest()
    {
        String accessToken = WechatUtils.getAccessToken();
        System.out.println(accessToken);
    }

    @Test
    public void test01()
    {
        String str = "2015,10,2015,8,2016,1,2016,2,2016,3";
        String[] array = str.split(",");
        Map<String, Integer> data = new HashMap<String, Integer>();
        for (int i = 0; i < array.length; i += 2)
        {
            if (data.containsKey(array[i]))
            {
                Integer oldValue = data.get(array[i]);
                Integer currentValue = Integer.parseInt(array[i + 1]);
                data.put(array[i], currentValue + oldValue);
            }
            else
            {
                data.put(array[i], Integer.parseInt(array[i + 1]));
            }
        }

        for (Map.Entry<String, Integer> entry : data.entrySet())
        {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
