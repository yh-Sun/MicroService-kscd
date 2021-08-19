package com.sun.structure.left_god.class02;

/**
 * 归并排序
 *
 * @author  Sun
 * @date    2021/8/19 9:09
 * @since   1.0
 */
public class Code01_MergeSort {

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 4, 1, 2, 3};
        new Code01_MergeSort().sort(arr, 0, arr.length - 1);

        // 打印
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 递归
     * merge：L R依次往新数组移动、最后遍历新数组到老数组的某一块内
     */
    public void sort(int[] arr, int L, int R) {
        if(L == R) {
            return ;
        }
        int mid = L + ((R - L) >> 1);
        sort(arr, L, mid);
        sort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public void merge(int[] arr, int L, int M, int R) {
        // 定义两个游标
        int p1 = L;
        int p2 = M + 1;

        // 定义数组，存放排序的数
        int[] temArr = new int[R - L + 1];
        int i = 0;

        // 左边往右游，中间往右游
        while (p1 <= M && p2 <= R) {
            temArr[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            temArr[i++] = arr[p1++];
        }
        while (p2 <= R) {
            temArr[i++] = arr[p2++];
        }

        // 将临时数组塞回L-R段
        /*for (int j = 0; j < temArr.length; j++) {
            arr[L + j] = temArr[j];
        }*/
        System.arraycopy(temArr, 0, arr, L, temArr.length);


    }


}
