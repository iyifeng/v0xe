package com.v0ex.lintcode;

/**
 * 最大子数组
 * 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。
 * 子数组最少包含一个数
 * 给出数组[−2,2,−3,4,−1,2,1,−5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 */
public class Solution41 {

    public int maxSubArray(int[] nums){
        if (nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] global = new int[2];
        int[] local = new int[2];
        global[0] = nums[0];
        local[0] = nums[0];
        for (int i = 0; i < n; i++) {
            local[i%2] = Math.max(nums[i],local[(i-1)%2] + nums[i]);
            global[i%2] = Math.max(local[i%2],global[i%2]);
        }
        return global[(n-1)%2];
    }

    public int maxSubArray1(int[] A){
        if (A == null || A.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE,sum=0,minSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max,sum-minSum);
            minSum = Math.min(minSum,sum);
        }
        return max;
    }

    public int maxSubArray2(int[] A){
        if (A == null || A.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE,sum = 0;
        for (int i = 0; i < A.length ; i++) {
            sum += A[i];
            max = Math.max(max,sum);
            sum = Math.max(sum,0);
        }
        return max;
    }
}
