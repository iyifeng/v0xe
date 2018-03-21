package com.v0ex.lintcode;

/**
 * 简单
 * 设计一个算法，计算出n阶乘中尾部零的个数
 * 11! = 39916800，因此应该返回 2
 */
public class Solution2 {

    /*
     * @param n: An integer
     * @return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        long sum = 0 ;
        while(n != 0){
            sum += n/5;
            n /= 5;
        }
        return sum;
    }
}
