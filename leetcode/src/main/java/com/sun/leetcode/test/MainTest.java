package com.sun.leetcode.test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


/**
 * 在1000万个不重复的整数中，找出最大的100个数。
 * 要求：1.原始数据集存在于内存
 *      2.打印出程序运行时间
 *      3.使用Java语言
 *      4.实现方法可以是多种
 *
 * @author  Sun
 *
 */
public class MainTest {

    /** 1000万个数 */
    public static int nums = 10000000;
    /** 最大100个数 */
    public static int n = 100;

    /**
     * 1.创建100个数据的堆，遍历1000万数据
     * 2.直接快排，去100个
     * 3.5个线程都找出最大的100个，再将500个merge成100个
     */
    public static void main(String[] args) {
        // 获取1000万个不重复随机整数
        int[] arr = randomArr(nums);

        {
            // 1.使用Queue构造堆，获取最大100个数
            long start = System.currentTimeMillis();

            int[] maxNum = getMaxNum(arr, n);

            long end = System.currentTimeMillis();
            System.out.println("采用堆-耗时：" + (end - start) + ", 最大的100个数：" + Arrays.toString(maxNum));
        }

        {
            // 2.直接排序，取最大的100个，此方法更改了原始数组
            long start = System.currentTimeMillis();

            quickSort(arr, 0, arr.length - 1);
            // 取出(nums-n, nums)的数
            int[] maxNum = new int[n];
            int max = 0;
            for (int i = arr.length - n; i < arr.length; i++) {
                maxNum [max++] = arr[i];
            }

            long end = System.currentTimeMillis();
            System.out.println("采用排序-耗时：" + (end - start) + ", 最大的100个数：" + Arrays.toString(maxNum));
        }

    }


    /**
     * 构建队列，大于队列中最小的，替换
     */
    public static int[] getMaxNum(int[] arr, int n) {
        Queue<Integer> max = new PriorityQueue<>(n);
        // 循环比较
        for (int e : arr) {
            if (max.size() < n) {
                max.offer(e);
            } else if (e > max.peek()) {
                max.poll();
                max.offer(e);
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = max.poll();
        }
        return res;
    }


    /**
     * 快排
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left - 1;
        int j = right + 1;
        int x = nums[left];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                swap(nums, i, j);
            }
        }
        quickSort(nums, left, j);
        quickSort(nums, j + 1, right);
    }


    /**
     * 工具方法-交换数组中两个数的值
     */
    public static void swap(int[] nums, int aNum, int bNum) {
        // 定义变量，交换a b的值
        int a = nums[aNum];
        int b = nums[bNum];
        // 交换
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        // 重新赋值
        nums[aNum] = a;
        nums[bNum] = b;
    }


    /**
     * 获取1000万个不重复的随机整数数组，放入内存中
     */
    public static int[] randomArr(int num){
        // 创建数组
        int[] arr = new int[num];
        // 获取0, 1, 2, 3 ..., 10000000
        for(int i = 0; i < num; i++) {
            arr[i] = i;
        }
        // 随机交换，形成不重复随机数组，最大的100个数 即为 10000000, 9999999, 9999998...
        Random r = new Random();
        for(int i = 0; i < num; i++) {
            int in = r.nextInt(num - i) + i;
            swap(arr, in, i);
        }
        return arr;
    }

}
