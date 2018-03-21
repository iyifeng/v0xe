package com.v0ex.lintcode;

/**
 * Created by zbj on 18/3/8.
 */
public class Solution191 {

    public static int maxProduct(int[] nums){
        int[] min = new int[nums.length];
        int[] max = new int[nums.length];
        min[0] = max[0] = nums[0];
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            min[i] = max[i] = nums[i];
            if (nums[i] > 0){
                max[i] = Math.max(max[i],max[i-1]*nums[i]);
                min[i] = Math.min(min[i],min[i-1]*nums[i]);
            }else if (nums[i] < 0){
                max[i] = Math.max(max[i],min[i-1]*nums[i]);
                min[i] = Math.min(min[i],max[i-1]*nums[i]);
            }
            result = Math.max(result,max[i]);
        }
        return result;
    }

    public static int maxProduct1(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int minPre = nums[0],maxPre = nums[0];
        int max = nums[0],min = nums[0];
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i],Math.max(maxPre*nums[i],minPre*nums[i]));
            min = Math.min(nums[i],Math.min(maxPre*nums[i],minPre*nums[i]));
            res = Math.max(max,min);
            maxPre = max;
            minPre = min;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[4];
        nums[0] = 2;
        nums[1] = 3;
        nums[2] = -2;
        nums[3] = 4;
        int i = maxProduct(nums);
        System.out.println(i);
    }
}
