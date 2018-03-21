package com.v0ex.lintcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单
 * Fizz Buzz 问题
 * 给你一个整数n. 从 1 到 n 按照下面的规则打印每个数：
 * 如果这个数被3整除，打印fizz.
 * 如果这个数被5整除，打印buzz.
 * 如果这个数能同时被3和5整除，打印fizz buzz.
 * 比如 n = 15, 返回一个字符串数组：
 * [
 * "1", "2", "fizz",
 * "4", "buzz", "fizz",
 * "7", "8", "fizz",
 * "buzz", "11", "fizz",
 * "13", "14", "fizz buzz"
 ]
 */
public class Solution9 {

    public static List<String> fizzBuzz(int n){
        List<String> results = new ArrayList<>();
        int i = 1;
        int p3 =1 ,p5 = 1;
        while (i <= n){
            while (i < p3*3 && i < p5*5){
                results.add(i+"");
                i++;
            }
            if (i <= n && p3*3 == p5*5){
                results.add("fizz buzz");
                p3++;
                p5++;
                i++;
                continue;
            }
            while (i <= n && p3*3 <=i ){
                results.add("fizz");
                p3++;
                i++;
            }
            while (i <= n && p5*5 <= i){
                results.add("buzz");
                p5++;
                i++;
            }
        }
        return results;
    }

    public static List<String> fizzBuzz1(int n){
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i%15 == 0){
                results.add("fizz buzz");
            }else if (i%5 == 0){
                results.add("buzz");
            }else if (i%3 == 0){
                results.add("fizz");
            }else {
                results.add(String.valueOf(i));
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<String> list = fizzBuzz1(15);
        System.out.println(JSON.toJSONString(list));
    }
}
