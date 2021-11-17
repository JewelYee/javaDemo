package com.yee.demo.leecode.tree;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/17 2:09 PM
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
