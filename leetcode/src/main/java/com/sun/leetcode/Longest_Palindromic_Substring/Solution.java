package com.sun.leetcode.Longest_Palindromic_Substring;

public class Solution {

    public static void main(String[] args) {
        int i = Integer.parseInt("33213213123123");

        String s = longest("babadbabadbabadbabadbabadbabad");
        System.out.println(s);
        // 我说为啥暴力解法这么快，原来是错误答案，zao
        System.out.println("--------------------------------------");
        String s1 = longest1("babadbabadbabadbabadbabadbabad");
        System.out.println(s1);

        System.out.println("--------------------------------------");
        String s2 = longest2("babad");
        System.out.println(s2);
    }

    /**
     * 动态规划
     * P[i][j] = P[i+1][j-1] && s(i) == s(j)
     *
     * 结束条件：
     * P[i][i] = true
     * p[i][i+1] = s(i) == s(i+1)
     */
    public static String longest1(String s) {
        int n = s.length();
        boolean[][] P = new boolean[n][n];
        int start = 0;
        int mx = 1;

        int count = 1;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                System.out.println(count++);
                boolean b = s.charAt(i) == s.charAt(j);
                if(j - i < 2) {
                    P[i][j] = b;
                } else {
                    P[i][j] = P[i+1][j-1] && b;
                }
                if(P[i][j] && mx < j - i + 1) {
                    mx = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + mx);
    }


    /**
     * 扩展中心法
     */
    public static String longest2(String s) {
        int start = 0;
        int end = 0;

        // 两种扩展方式
        for (int i = 0; i < s.length(); i++) {
            // 1、从中心扩展[i, i]
            int len1 = longest2Method(s, i, i);
            int len2 = longest2Method(s, i, i + 1);
            int max = Math.max(len1, len2);
            if(max > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int longest2Method(String s, int left, int right) {
        while (left >= 0
                && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left + 1;
    }


    /**
     * 暴力解法
     */
    public static String longest(String s) {
        int iIndex = 0;
        int jIndex = 0;
        int res = 0;
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length() - i; j++) {
                System.out.println("暴力" + count++);
                if(s.charAt(i) == s.charAt(j)) {
                    if(j - i >= res) {
                        res = j - i;
                        iIndex = i;
                        jIndex = j;
                    }
                }
            }
        }

        return s.substring(iIndex, jIndex + 1);
    }

}
