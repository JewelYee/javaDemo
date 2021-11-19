package com.yee.demo.leecode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/18 1:42 PM
 */
public class RandomCollection {


    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int all = res.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }

    private static void backtrack(int i, int[] nums, List<List<Integer>> res, List<Integer> tmp) {
        //
        res.add(new ArrayList<>(tmp));

        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
    public static void main(String[] args) {
        int []nums = {1,2,3};
        //1.
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        System.out.println(res.toString());

        //2.
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets.toString());
    }



}
