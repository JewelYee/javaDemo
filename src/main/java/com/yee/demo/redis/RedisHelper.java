package com.yee.demo.redis;

import com.yee.demo.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis 相关操作 助手类
 *
 * @Author Lobo
 * @Date 2020/9/18
 */
@Slf4j
public final class RedisHelper {

    private static StringRedisTemplate stringRedisTemplate;


    /**
     * 获取redis实例
     *
     * @return
     */
    static {
        if (null == stringRedisTemplate) {
            stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        }
    }

    /**
     * 设置key
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static void set(String key, String value, long time) {
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public static void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置key
     *
     * @param key 键
     * @return
     */
    public static String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置key
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean setnx(String key, String value, long time) {
        try {
            return stringRedisTemplate.opsForValue().setIfAbsent(key, value, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("【需关注】Redis setnx异常...key:{}", key, e);
            throw e;
        }
    }

    /**
     * 设置key
     * (带等待时间，等待时间结束依旧未获取到锁返回false)
     *
     * @param key        键
     * @param value      值
     * @param expireTime 时间(秒)
     * @param awaitTime  等待超时时间(毫秒)
     * @return
     */
    public static boolean setnxForWaiting(String key, String value, long expireTime, int awaitTime) {
        int usedTime = 0;
        int sleepTime = 100;//休眠单位
        while (true) {
            boolean ret = setnx(key, value, expireTime);
            if (ret || usedTime >= awaitTime) {
                return ret;
            }
            usedTime += sleepTime;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                log.error("redis setnxForWaiting exception...key:{}", key, e);
            }
        }
    }

    /**
     * 设置key失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */

    public static void expire(String key, long time) {
        stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 删除key
     *
     * @param key 键
     * @return
     */

    public static boolean del(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 删除key
     *
     * @param key 键
     * @return
     */

    public static boolean exist(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 删除key
     *
     * @param key 键
     * @return
     */

    public static boolean delIgnoreException(String key) {
        try {
            return del(key);
        } catch (Exception e) {
            log.error("redis key 删除异常...key:{}", key, e);
            return false;
        }
    }

    public String buildRedisKey(String preffix, String suffix) {
        return new StringBuilder(preffix).append(suffix).toString();
    }
}