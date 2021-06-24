package com.sun.leetcode.tow_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 
 * @author  Sun
 * @date    2021/6/23 9:41
 * @since   1.0
 */
public class Solution {
    
    public static void main(String[] args) {
        int[] nums = {2, 11, 7, 15};
        int[] i = new Solution().twoSum(nums, 18);
        System.out.println(Arrays.toString(i));
    }
    
    
    /**
     * 暴力解法
     * 
     * @author  Sun
     * @date    2021/6/23 9:52
     * @param	nums: 数组
     * @param	target: 目标值
     * @return	服务结果的数组下标
     **/
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
               if(target ==  nums[i] + nums[j]) {
                   return new int[]{i, j};
               }
            }
        }
        return new int[0];
    }


    /**
     * 降低服务度解法，使用map
     * 
     * @author  Sun
     * @date    2021/6/23 14:57
     * @param	nums: 数组
     * @param	target: 目标值
     * @return	服务结果的数组下标
     **/
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0};
    }

}
