package com.yee.demo.leecode.list;

/**
 * @Desciption: 翻转链表
 * @Auther: yee
 * @Date:2021/11/17 1:50 PM
 */
public class ReverseList {
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null; //前指针节点
        ListNode curr = head; //当前指针节点
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null) {
            ListNode nextTemp = curr.next; //临时节点，暂存当前节点的下一节点，用于后移
            curr.next = prev; //将当前节点指向它前面的节点
            prev = curr; //前指针后移
            // 需要将临时节点赋值给curr
            // 如果用curr=curr.next，由于开始curr.next已经指向了prev，也就是初始的值null,所以curr=curr.next也就是p=null了，所以就退出循环了
            curr = nextTemp; //当前指针后移
        }
        return prev;
    }

}
