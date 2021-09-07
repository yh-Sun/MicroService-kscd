package com.sun.leetcode.test;

import java.util.Arrays;
import java.util.Stack;


public class HeapSort {

        public static void heapSort(int[] arr) {
            if (arr == null && arr.length < 2) {
                return;
            }
            // 先构建大顶堆
            for (int i = 0; i < arr.length; i++) {
                headInsert(arr, i);
            }

            // 获取100个
            getMaxNum(arr);
        }

        //上升
        public static void headInsert(int[] arr, int cur) {
            while (arr[cur] > arr[(cur - 1) / 2]) {
                swap(arr, cur, (cur - 1) / 2);
                cur = (cur - 1) / 2;
            }
        }

        //下降
        public static void headify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;//最大值下标
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private static void swap(int[] arr, int x, int y) {
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }

        public static void main(String[] args) {
            int[] arr = {1, 5, 6, 3, 7, 4, 0};
            heapSort(arr);
            System.out.println(Arrays.toString(arr));
        }

        public static void getMaxNum(int[] arr) {
            Stack<Integer> stack = new Stack<>();
            int[] finalArr = new int[3];
            stack(arr, finalArr, 0, 0, stack);

            System.out.println("1");
        }

        public static void stack(int[] arr, int[] finalArr, int i, int fi, Stack<Integer> stack) {
            if(fi >= 10) {
                return;
            }
            if(fi == 0) {
                finalArr[fi++] = arr[0];
            } else {
                finalArr[fi++] = arr[stack.pop()];
            }

            int left = 2 * i + 1;
            int right = 2 * i + 2;
            stack.push(left);
            stack.push(right);

            stack(arr, finalArr, left, fi, stack);
            stack(arr, finalArr, right, fi, stack);
        }

    }
