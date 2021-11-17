package com.yee.demo.leecode.list;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/17 2:12 PM 1
 */
public class ListNode {

    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
