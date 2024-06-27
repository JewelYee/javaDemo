package com.yee.demo.url;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author yee
 * 2023/6/13 11:42
 */
public class CryptoUtil {

    /**
     * 计算 HMAC-SHA1 算法消息摘要
     * @param message 要计算哈希值的消息
     * @param key 用于计算哈希值的密钥
     * @return 哈希值
     * @throws NoSuchAlgorithmException 如果指定的算法名称无效
     * @throws InvalidKeyException 如果指定的密钥无效
     */
    public static byte[] hmacSha1(byte[] message, byte[] key) {
        try{
            SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(keySpec);
            return mac.doFinal(message);
        }catch (Exception ex) {

        }
        return null;
    }
}
