package com.sun.leetcode.Add_Two_Numbers;

import lombok.Data;

/**
 * 2. 两数相加
 *
 * @author  Sun
 * @date    2021/6/23 15:59
 * @since   1.0
 */
@Data
public class ListNode<E> {

    private static class Node<E> {

        E item;

        Node<E> next;

        public Node() {
        }

        public Node(E item) {
            this.item = item;
        }

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    transient Node<E> head;
    int size;

    public ListNode() {
        size = 0;
    }


    /**
     * 链表：添加元素
     *
     * @author  Sun
     * @date    2021/6/24 10:05
     **/
    public void add(E e) {
        Node<E> node = new Node<>(e, null);
        // 根节点
        if(head == null) {
            head = node;
            size++;
            return;
        }

        // 指向临时变量寻找插入位置
        Node<E> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        // 你是最后一个，就在我屁股后呆着吧
        tmp.next = node;
        size++;
    }


    /**
     * 本例中get为取出根节点，并将跟节点的子节点设置为根节点（如果使用index指针，只是取出，并不会修改链表根节点）
     *
     * @author  Sun
     * @date    2021/6/24 10:10
     **/
    public E get() {
        Node<E> tmp = head;
        // 没了，直接返回空，正常应该通过size判断
        if(tmp == null) {
            return null;
        }
        E item = tmp.item;
        head = tmp.next;

        size--;
        return item;
    }

}
