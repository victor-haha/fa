package com.yidu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 类的描述:编号生成，工具类
 *
 * @author wh
 * @since 2020/9/7 16:31
 */
public class NoUtils {
    /**
     * 编号生成方法
     * @param prefix 编号前缀
     * @return 加工后的编号  如：JJKC-yyyy-MM-dd-HHmmss
     */
    public static String getNo(String prefix){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date())+ UUID.randomUUID().toString().replace("-","").substring(0,6);

        return prefix + format;
    }

    public static void main(String[] args) {
        String str = getNo("JJKC");
        System.out.println(str);
    }
}
