package com.sun.structure.left_god;

/**
 * arr[L...R]范围求最大值
 *
 * @author  Sun
 * @date    2021/8/16 17:25
 * @since   1.0
 */
public class Code08_GetMax {

    public static void main(String[] args) {
        int[] arr = {42, 84, 51, 1, 2, 4, 12, 42, 99, 51, 2, 74};
        int max = getMax(arr, 0, arr.length - 1);
        System.out.println(max);    // 99
    }

    public static int getMax(int[] arr, int L, int R) {
        if(L == R) {
            return arr[L];
        }

        // 取中间；L + (R-L)/2，除2就代表向右移一位
        int mid = L + ((R-L) >> 1);

        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

}
