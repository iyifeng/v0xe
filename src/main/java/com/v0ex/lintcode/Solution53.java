package com.v0ex.lintcode;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 单词的构成：无空格字母构成一个单词
 * 输入字符串是否包括前导或者尾随空格？可以包括，但是反转后的字符不能包括
 * 如何处理两个单词间的多个空格？在反转字符串中间空格减少到只含一个
 */
public class Solution53 {

    public static String reverseWords(String s) {
        if (null == s || 0 == s.length()) return "";
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = array.length-1;i>=0;--i){
            if (!"".equals(array[i])){
                sb.append(array[i]).append(" ");
            }
        }
        //return 0 == sb.length() ? "" : sb.toString();
        return 0 == sb.length() ? "" : sb.substring(0,sb.length()-1);
    }

    public static void main(String[] args) {
        String str = "this is a test ";
        String result = reverseWords(str);
        System.out.println("=="+result+"==");
    }
}
