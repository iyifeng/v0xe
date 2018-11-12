package com.v0ex.leetcode;

import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * @author yifeng
 * @date 17/10/13
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int y = target - nums[i];
            if (map.containsKey(y)){
                return new int[]{i,map.get(y)};
            }else {
                map.put(nums[i],i);
            }
        }
        return new int[0];
    }
}
