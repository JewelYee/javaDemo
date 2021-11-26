package com.yee.demo.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/26 4:09 PM
 */
public class CacheProblemBoomFilter {

    String charsetName = "utf-8";

    BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName(charsetName)), 1000, 0.001);

    // keys
    List<String> keys = new ArrayList<>();
    //把所有数据存入布隆过滤器
    void init() {
        for (String key : keys) {
            bloomFilter.put(key);
        }
    }


    String getFromBloomFilter(String key) {
        // 从布隆过滤器这一级缓存判断下key是否存在
        Boolean exist = bloomFilter.mightContain(key);
        if (!exist) {
            return "";
        }
        // 从缓存中获取数据
        String cacheValue = RedisHelper.getStr(key);
        // 缓存为空
        if (StringUtils.isBlank(cacheValue)) {
            // 从存储中获取
            String storageValue = "";
            RedisHelper.set(key, storageValue);
            // 如果存储数据为空， 需要设置一个过期时间(300秒)
            if (storageValue == null) {
                RedisHelper.expire(key, 60 * 5);
            }
            return storageValue;
        } else {
            // 缓存非空
            return cacheValue;
        }
    }
}
