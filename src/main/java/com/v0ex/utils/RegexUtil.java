package com.v0ex.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zbj on 16/6/7.
 */
public class RegexUtil {

    /**
     * 密码不可为空，长度至少8位，包含数字、字母大写、字母小写、特殊字符中的任意三种组合
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password){
        AtomicInteger flag = new AtomicInteger(-3);
        String regStr1 = ".*\\d.*";
        String regStr2 = ".*[a-z].*";
        String regStr3 = ".*[A-Z].*";
        String regStr4 = ".*\\S.*";
        if (password.length()<8)
            return false;
        if (password.matches(regStr1)){
            flag.incrementAndGet();
        }
        if (password.matches(regStr2)){
            flag.incrementAndGet();
        }
        if (password.matches(regStr3)){
            flag.incrementAndGet();
        }
        if (password.matches(regStr4)){
            flag.incrementAndGet();
        }
        if (flag.get()<0)
            return false;
        return true;
    }
}
