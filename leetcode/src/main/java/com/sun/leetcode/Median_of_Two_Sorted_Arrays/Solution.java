package com.sun.leetcode.Median_of_Two_Sorted_Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double v1 = findM1(nums1, nums2);
        double v2 = findM2(nums1, nums2);
        System.out.println(v1 + "," + v2);
    }

    /**
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
     *
     * A: 1 3 4 9
     *        ↑
     * B: 1 2 3 4 5 6 7 8 9
     *        ↑
     */
    public static double findM1(int[] A, int[] B) {
        // 获取A、B的长度
        int aLen = A.length;
        int bLen = B.length;

        // 计算中位坐标，先以奇数为准
        int k = (aLen + bLen + 1) >> 1;

        // 定义A、B对应的滑动指针aStart,bStart
        int aStart = 0, bStart = 0;

        do {
            int kk = (k / 2 - 1);
            aStart += kk;
            bStart += kk;
            if (A[aStart] > B[bStart]) {
                aStart -= kk;
            } else {
                bStart -= kk;
            }
            k = k - k / 2;

        } while (k != 1);

        int res;
        aStart++;
        bStart++;
        res = Math.min(A[aStart], B[bStart]);
        return res;
    }


    /**
     * 分治法
     * 1 2 3 4 5  6 7 8 9 10
     *         ↑  ↑
     *         4  5
     */
    public static double findM2(int[] A, int[] B) {
        // 10 -> 4，5    9 -> 4
        int aL = A.length;
        int bL = B.length;
        // 定义中位数坐标
        int left;
        int right = (aL + bL) >> 1;
        // 偶数，left = right - 1；奇数，left = right
        if(((aL + bL) & 1) == 0) {
            left = right - 1;
        } else {
            left = right;
        }

        int leftNum = median2(A, B, left + 1);
        int rightNum = median2(A, B, right + 1);
        return (leftNum + rightNum) / 2.0;
    }

    /**
     * 注意k应传多少个数字，而不是指针坐标
     */
    private static int median2(int[] A, int[] B, int k) {
        int aIndex = 0, bIndex = 0;
        // 以left为准，每次取中位
        while (true) {
            // 如果A没了，剩下的都取B; 此处为什么不是 lenth - 1呢，因为处理完逻辑要 往后跳一下 到新位置
            if(aIndex == A.length) {
                return B[bIndex + k - 1];
            }
            // 如果B没了，剩下的都取A
            if(bIndex == B.length) {
                return A[aIndex + k - 1];
            }
            // 边界条件
            if(k == 1) {
                return Math.min(A[aIndex], B[bIndex]);
            }

            // 分治法
            int half = k >> 1;
            int newAIndex = Math.min(aIndex + half, A.length) - 1;
            int newBIndex = Math.min(bIndex + half, B.length) - 1;
            if(A[newAIndex] > B[newBIndex]) {
                // A大，则B左边全剔除掉；左闭右闭bIndex和newBIndex都要剔除掉，所以要+1
                k -= (newBIndex - bIndex + 1);
                // 因为newBIndex已经被剔除掉了，所以B向右边跳一下
                bIndex = newBIndex + 1;
            } else {
                k -= (newAIndex - aIndex + 1);
                aIndex = newAIndex + 1;
            }
        }
    }


    /**
     * 常规思想
     */
    public static double findMedianSortedArrays(int[] A, int[] B) {
        // 获取A、B的长度
        int aLen = A.length;
        int bLen = B.length;
        int len = aLen + bLen;
        // 定义左右两个数字的坐标值
        int left = -1;
        int right = -1;
        // 定义A、B对应的滑动指针aStart,bStart
        int aStart = 0, bStart = 0;

        for (int i = 0; i <= len >> 1; i++) {
            // 精髓，右边给左边，因为right将要赋值新的值
            left = right;

            if(aStart < aLen && (bStart >= bLen || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }

        // 基数偶数
        if((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }


    /**
     * 小太阳，时间复杂度：O(n/2)
     */
    public static double medianOfTwoSortedArrays(int[] nums1, int[] nums2) {
        // 定义两个指针，i操控nums1，j操控nums2
        int i = 0, j = 0;
        // 定义要游标到的具体中位坐标：9 = 指针4，10 = 指针4，5
        int num = (nums1.length + nums2.length + 1) >> 1;
        boolean jiOrOu = (nums1.length + nums2.length) % 2 == 1;

        double res;
        int ii = 0;
        int jj = 0;

        // 双向指针一起挪动到num
        for (int count = 0; count < num; count++) {
            if(i >= nums1.length) {
                ii = nums2[j++];
            }
            if(j >= nums2.length) {
                ii = nums1[i++];
            }

            if(i < nums1.length && j < nums2.length) {
                int ni = nums1[i];
                int nj = nums2[j];
                if(ni < nj) {
                    ii = ni;
                    i++;
                } else {
                    ii = nj;
                    j++;
                }
            }

        }

        if(!jiOrOu) {
            if(i >= nums1.length) {
                jj = nums2[j];
            }
            if(j >= nums2.length) {
                jj = nums1[i];
            }
            if(i < nums1.length && j < nums2.length) {
                if(nums1[i] < nums2[j]) {
                    jj = nums1[i];
                } else {
                    jj = nums2[j];
                }
            }
            res = (double)(ii + jj) / 2;
        } else {
            res = ii;
        }
        return res;
    }

}
