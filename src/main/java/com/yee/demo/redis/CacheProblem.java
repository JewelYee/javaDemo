package com.yee.demo.redis;

import org.apache.commons.lang3.StringUtils;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/26 3:02 PM
 */
public class CacheProblem {



    /**
     * 缓存空对象
     *
     */
    public static String get(String key){
        // 从缓存中获取数据
        String cacheValue = RedisHelper.getStr(key);
        // 缓存为空
        if (StringUtils.isBlank(cacheValue)) {
            // 从DB中获取
            String storageValue = "abc";
            // 获取后放入redis
            RedisHelper.set(key, storageValue);
            // 如果存储数据为空， 需要设置一个过期时间(300秒)
            if (storageValue == null) {
                RedisHelper.expire(key, 60 * 5);
            }
            return storageValue;

        }else{
            // 缓存非空
            return cacheValue;
        }
    }



}
