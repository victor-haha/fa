package com.yidu.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/7/27 10:01
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
}
