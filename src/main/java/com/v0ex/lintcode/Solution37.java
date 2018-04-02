package com.v0ex.lintcode;

/**
 * 入门
 * 反转一个3位整数
 * 123 反转之后是 321。
 * 900 反转之后是 9。
 */
public class Solution37 {

    public static int reverseInteger(int number) {
        // write your code here
        int result = 0;
        while (number != 0) {
            result = result * 10 + number % 10;
            number = number / 10;
        }
        return result;
    }
}
