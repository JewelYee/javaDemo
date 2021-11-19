package com.yee.demo.interview;

/**
 *
 * 单例模式
 *
 * Date: 2021/10/27 8:12 下午
 */
public class Singleton {

    private static Singleton singelton;
    private Singleton(){}
    public static Singleton getSingelton() {
            if (singelton == null){
                synchronized (singelton) {
                    if (singelton == null){
                        singelton = new Singleton();
                    }
                }
            }
        return singelton;
    }
}
