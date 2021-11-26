package com.yee.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/26 4:29 PM
 */
public class RedisLock {

    public static void main(String[] args) {
        RedissonClient redisson = Redisson.create();
        RLock lock = redisson.getLock("anyLock");
        lock.lock();
        //业务逻辑
        lock.unlock();
    }

}
