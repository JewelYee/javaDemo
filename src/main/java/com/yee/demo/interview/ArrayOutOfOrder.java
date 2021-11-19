package com.yee.demo.interview;

import java.util.Random;

/**
 * 数组乱序
 * Date: 2021/11/18 5:08 下午
 */
public class ArrayOutOfOrder {

    /**
     * 每次从已知数组随机一个数，然后将数组的最后一个值 赋值给前面随机到的数的位置上，然后将长度-1，再从原数组下标-1的数组中随机。
     * 运算次数就是数组长度。
     * 思路：
     * 假设数组array长度为n。
     * 用标准随机函数rand(n)生成[0, n-1]之间的一个随机数，将array[n-1]和array[rand(n)]交换，这样就把array[n-1]位置上的数确定了；
     * 再将array[n-2]和array[rand(n-1)]交换，确定array[n-2]位置上的数;
     * 再将array[n-3]和array[rand(n-2)]交换，确定array[n-3]位置上的数;
     * 。。。。。。
     * 最后将array[0]和array[rand(1)]交换（实际就是自己和自己交换），把array[0]确定了
     * 这样生成的array[0..n-1]的数组是完全随机的乱序，且时间复杂度为O(n)，空间复杂度为O(1)
     */

    public static Integer []  outOfOrder(Integer[] arr) {
        Integer [] newArr = new Integer[arr.length];
        int count = arr.length;
        int cbRandCount = 0;// 索引
        int cbPosition = 0;// 位置
        int k = 0;
        do {
            Random rand = new Random();
            int r = count - cbRandCount;
            cbPosition = rand.nextInt(r);
            newArr[k++] = arr[cbPosition];
            cbRandCount++;
            arr[cbPosition] = arr[r - 1];// 将最后一位数值赋值给已经被使用的cbPosition
        } while (cbRandCount < count);

        return newArr;
    }
}
