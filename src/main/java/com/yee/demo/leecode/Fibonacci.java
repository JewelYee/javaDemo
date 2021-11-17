package com.yee.demo.leecode;

/**
 * @Desciption: 斐波那契数列
 * @Auther: yee
 * @Date:2021/11/17 2:21 PM
 */
public class Fibonacci {


    public static int fibonacci(int n){

        if (n == 1 || n == 2) {             //特殊情况，分开讨论
            return 1;
        }
        if (n > 2) {
            return fibonacci(n - 1) + fibonacci(n - 2);     //递归调用
        }
        return -1;              //如果输入错误的n，一律返回-1
    }

}
