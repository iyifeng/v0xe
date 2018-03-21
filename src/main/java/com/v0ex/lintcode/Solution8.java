package com.v0ex.lintcode;

/**
 * 给定一个字符串和一个偏移量，根据偏移量旋转字符串(从左向右旋转)
 * 样例
 * 对于字符串 "abcdefg".
 * offset=0 => "abcdefg"
 * offset=1 => "gabcdef"
 * offset=2 => "fgabcde"
 * offset=3 => "efgabcd"
 */
public class Solution8 {

    public static void rotateString(char[] str , int offset){
        if (null == str || 0 == str.length)return;
        offset = offset % str.length;
        reverse(str,0,str.length-offset-1);
        reverse(str,str.length-offset,str.length-1);
        reverse(str,0,str.length-1);
    }

    private static   void reverse(char[] str, int start ,int end){
        for (int i = start,j=end;i < j;i++,j--){
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }

    public static void main(String[] args) {
        char[] str = new char[]{'a','b','c','d'};
        rotateString(str,4);
        System.out.println(str.toString());
    }
}
