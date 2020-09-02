package com.yidu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/7/22 17:40
 */
public class DateUtils {

    //日期转换成字符串
    public static String dataToString(Date date,String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;

    }
    //字符串转换传成日期
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }
}
