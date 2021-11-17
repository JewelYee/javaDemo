package com.yee.demo.leecode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Desciption: 中序遍历
 * @Auther: yee
 * @Date:2021/11/17 2:08 PM
 */
public class InorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }
}
