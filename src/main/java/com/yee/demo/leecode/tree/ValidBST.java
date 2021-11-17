package com.yee.demo.leecode.tree;


import com.yee.demo.leecode.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Desciption: 验证二叉树
 * @Auther: yee
 * @Date:2021/11/17 11:40 AM
 */
public class ValidBST {

    /**
     * 验证二叉树
     * 记录最小值和最大值
     * 递归左边时, 最小值为上次的最小值, 最大值为当前值
     * 递归右边时, 最小值为当前值, 最大值为上次最大值
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        // 左边必须小于中间
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // 递归左边时, 最小值为上次的最小值, 最大值为当前值
        // 递归右边时, 最小值为当前值, 最大值为上次最大值
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }


    /**
     * 中序遍历
     * @param root
     * @return
     */
    public static boolean isValidBST1(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


}
