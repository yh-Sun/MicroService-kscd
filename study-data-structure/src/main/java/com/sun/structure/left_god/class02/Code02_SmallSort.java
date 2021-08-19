package com.sun.structure.left_god.class02;

/**
 * 小和问题
 *
 * @author  Sun
 * @date    2021/8/19 10:41
 * @since   1.0
 */
public class Code02_SmallSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        int i = new Code02_SmallSort().smallSort(arr, 0, arr.length - 1);
        System.out.println(i);
    }

    public int smallSort(int[] arr, int L, int R) {
        if(L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        int left = smallSort(arr, L, mid);
        int right = smallSort(arr, mid + 1, R);
        return left + right + mergeAdd(arr, L, mid, R);
    }

    public int mergeAdd(int[] arr, int L, int M, int R) {
        // 定义临时数组
        int[] temArr = new int[R - L + 1];
        int add = 0;

        // 定义游标
        int p1 = L;
        int p2 = M + 1;
        int temI = 0;

        // 归并
        while (p1 <= M && p2 <= R) {
            add += arr[p1] < arr[p2] ? arr[p1] * (R-p2 + 1) : 0;
            temArr[temI++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
           temArr[temI++] = arr[p1++];
        }
        while (p2 <= R) {
            temArr[temI++] = arr[p2++];
        }
        System.arraycopy(temArr, 0, arr, L, temArr.length);
        return add;
    }

}
