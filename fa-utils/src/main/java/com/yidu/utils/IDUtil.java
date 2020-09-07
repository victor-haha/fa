package com.yidu.utils;

import java.util.UUID;

/**
 * 类的描述:生成随机id
 *
 * @Author wh
 * @Date 2020/6/5 9:15
 */
public class IDUtil {
    private IDUtil(){}

    /**
     * 生成16位随机字符串
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","").substring(0,16);
    }
}
