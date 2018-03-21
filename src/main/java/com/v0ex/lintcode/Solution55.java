package com.v0ex.lintcode;

import com.alibaba.fastjson.JSON;

/**
 * 简单
 * 比较字符串
 * 比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是 大写字母
 * 给出 A = "ABCD" B = "ACD"，返回 true
 * 给出 A = "ABCD" B = "AABC"， 返回 false
 */
public class Solution55 {

    public static boolean compareStrings(String A,String B){
        int[] counts = new int[26];
        for(int i = 0 ; i < 26 ;i++){
            counts[i] = 0;
        }
        for(int i = 0;i < A.length();i++){
            counts[A.charAt(i) -'A'] ++;
            System.out.println(JSON.toJSONString(counts));
        }
        for(int i = 0; i < B.length();i++){
            counts[B.charAt(i) -'A'] --;
            if(counts[B.charAt(i) -'A'] < 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int d = 'A';
        System.out.println(d);
        compareStrings("ZHAOBINJIE","OB");
    }
}
