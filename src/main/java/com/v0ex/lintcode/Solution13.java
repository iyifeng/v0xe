package com.v0ex.lintcode;

/**
 * 对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
 *
 * 在面试中我是否需要实现KMP算法？
 *
 * 不需要，当这种问题出现在面试中时，面试官很可能只是想要测试一下你的基础应用能力。当然你需要先跟面试官确认清楚要怎么实现这个题。
 * 如果 source = "source" 和 target = "target"，返回 -1。
 * 如果 source = "abcdabcdefg" 和 target = "bcd"，返回 1。
 */
public class Solution13 {

    public static int strStr(String source,String target){
        if (null == source || null == target)return -1;
        for (int i =0 ;i < source.length()-target.length()+1;i++){
            int j = 0;
            for (j = 0;j<target.length();j++){
                if (source.charAt(i+j)!=target.charAt(j))break;
            }
            if (j == target.length()){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int i = strStr("this is a test", "a");
        System.out.println(i);
    }
}
