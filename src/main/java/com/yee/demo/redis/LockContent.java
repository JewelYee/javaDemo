package com.yee.demo.redis;

import lombok.Data;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/29 1:56 PM
 */
@Data
public class LockContent {


    /**
     * 锁过期时间，单位秒
     */
    private volatile long lockExpire;

    /**
     * 锁过期时间，单位毫秒
     */
    private volatile long expireTime;

    /**
     * 获取锁的开始时间，单位毫秒
     */
    private volatile long startTime;

    /**
     * 用于防止锁的误删，全局唯一
     */
    private String requestId;

    /**
     * 执行业务的线程
     */
    private volatile Thread thread;

    /**
     * 业务调用设置的锁过期时间，单位秒
     */
    private long bizExpire;

    /**
     * 重入次数
     */
    private int lockCount = 0;



}
