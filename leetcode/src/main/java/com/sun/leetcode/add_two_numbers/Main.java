package com.sun.leetcode.add_two_numbers;

import cn.hutool.core.lang.Console;

public class Main {

    public static void main(String[] args) {

        ListNode<Integer> node1 = new ListNode<>();
        node1.add(2);
        node1.add(4);
        node1.add(3);

        ListNode<Integer> node2 = new ListNode<>();
        node2.add(5);
        node2.add(6);
        node2.add(4);

        ListNode<Integer> twoNumbers = new Main().twoNumbers(node1, node2);
        Console.log(twoNumbers);
    }

    /**
     * 链表：两数之和
     *
     * @author  Sun
     * @date    2021/6/24 10:05
     **/
    public ListNode<Integer> twoNumbers(ListNode<Integer> n1, ListNode<Integer> n2) {
        ListNode<Integer> result = new ListNode<>();
        int array = 0;
        do {
            Integer i1 = n1.get();
            Integer i2 = n2.get();
            int i = i1 + i2 + array;
            result.add(i % 10);
            array = i / 10;
        } while (n1.size != 0 || n2.size != 0);
        return result;
    }
    
}
