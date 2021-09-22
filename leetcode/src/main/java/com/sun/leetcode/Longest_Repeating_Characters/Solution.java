package com.sun.leetcode.Longest_Repeating_Characters;

import java.util.HashSet;
import java.util.Set;

class Solution {

    public static void main(String[] args) {
        int count = new Solution().longestRepeatingCharacters("pwwkew");
    }

    /**
     * Sun
     */
    public int longestRepeatingCharacters(String str) {
        int res = 0;
        Set<Character> list = new HashSet<>();
        for (int i = 0, j = 0; i < str.length(); i++) {
            // 遍历字符串，并统计每个字符串对应的 最长无重复字符串个数
            while (list.contains(str.charAt(i))) {
                list.remove(str.charAt(j++));
            }
            list.add(str.charAt(i));
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

}
