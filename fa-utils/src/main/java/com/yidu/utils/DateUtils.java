package com.yidu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

    /**
     * //字符串转换传成日期
     * @param str 格式还的日期
     * @param patt 以这种方式进行解析
     * @return 日期对象
     * @throws ParseException 抛出异常
     */
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }

    /**
     * 今天零点零分零秒的毫秒数
     * @param date 日期对象
     * @return 这个日期对象的零点零分零秒的毫秒数
     */
    public static Long ZeroDate(Date date){
        return date.getTime()/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();
    }

    public static void main(String[] args){
        System.out.println(DateUtils.ZeroDate(new Date()));

    }
}
