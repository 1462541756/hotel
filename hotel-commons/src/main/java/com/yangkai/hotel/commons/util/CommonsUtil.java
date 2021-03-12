package com.yangkai.hotel.commons.util;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author 杨锴
 * @date 2020/10/19 16:02
 * @description：
 */
public  class CommonsUtil {
    public static final String  CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-0123456789_abcdefghijklmnopqrstuvwxyz";

    //生成验证码
    public static String getRandomVerificationCode(int len){
        StringBuilder result= new StringBuilder();
        Random random =new Random();
        for (int i = 0; i <len; i++) {
            result.append(CODE.charAt(Math.abs(random.nextInt()) % CODE.length()));
        }
        return result.toString();
    }
    private static String regex = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";

    //邮箱格式验证
    public static boolean validEmail(String email) {
        return Pattern.compile(regex).matcher(email).find();
    }
}
