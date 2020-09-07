package com.yidu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        String format = sdf.format(new Date());

        return prefix+"-"+format;
    }

    public static void main(String[] args) {
        String str = getNo("JJKC");
        System.out.println(str);
    }
}
