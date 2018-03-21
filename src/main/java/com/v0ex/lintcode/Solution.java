package com.v0ex.lintcode;

import java.util.HashMap;

/**
 * 给出一个不含重复数字的排列，求这些数字的所有排列按字典序排序后该排列的编号。其中，编号从1开始。
 */
public class Solution {

    static long fac(int numerator){
        long now = 1;
        for (int i = 1; i < numerator; i++) {
            now *= (long)i;
        }
        return now;
    }

    static long generateNum(HashMap<Integer,Integer> hashMap){
        long denominator = 1;
        int sum = 0;
        for (int val : hashMap.values()){
            if (0 == val)continue;
            denominator *= fac(val);
            sum += val;
        }
        if (0 == sum)return sum;
        return fac(sum)/denominator;
    }

    public static long permutationIndex(int[] A){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < A.length ; i++) {
            if (hashMap.containsKey(A[i])){
                hashMap.put(A[i],hashMap.get(A[i])+1);
            }else {
                hashMap.put(A[i],1);
            }
        }
        long ans = 0;
        for (int i = 0; i < A.length ; i++) {
            for (int j = i+1; j < A.length ; j++) {
                if (A[j]<A[i]){
                    hashMap.put(A[j],hashMap.get(A[j])-1);
                    ans += generateNum(hashMap);
                    hashMap.put(A[j],hashMap.get(A[j])+1);
                }
            }
            hashMap.put(A[i],hashMap.get(A[i])-1);
        }
        return ans+1;
    }

    public static void main(String[] args) {
        int[] list = new int[]{3,5,7,9};
        long l = permutationIndex(list);
        System.out.println(l);
    }
}
