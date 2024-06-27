package com.yee.demo.url;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author yee
 * 2023/6/13 11:17
 */
@Slf4j
public class ShortUrlGenerator {

    // 以下密钥和向量仅供测试使用，正式环境下请使用更安全的密钥和向量
    private static final byte[] BYTE_KEY = "SomeTestKey".getBytes();
    private static final SecretKeySpec KEY = new SecretKeySpec(BYTE_KEY, "AES");
    private static final String CHARSET_NAME = "UTF-8";
    private static final int NUM_BYTES_IV = 16;
    private static final int KEY_LENGTH = 128; // 指定密钥长度为 128 位

    // 将密钥扩展为指定长度
    private static byte[] extendKey(byte[] byteKey, int keyLength) {
        ByteBuffer buffer = ByteBuffer.allocate(keyLength / 8);
        buffer.put(byteKey);
        byte[] extendedKey = buffer.array();
        return extendedKey;
    }

    public static String encrypt(String url)  {
        try {
            // 扩展密钥至指定长度
            byte[] extendedKey = extendKey(BYTE_KEY, KEY_LENGTH);

            // 创建 AEAD 加密器
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            // 创建随机 Initialization Vector
            byte[] iv = new byte[NUM_BYTES_IV];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            // 初始化加密器
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(extendedKey, "AES"), new IvParameterSpec(iv));

            // 加密 URL
            byte[] urlBytes = url.getBytes(CHARSET_NAME);
            byte[] encryptedUrlBytes = cipher.doFinal(urlBytes);

            // 组合 IV、加密数据和鉴别码
            ByteBuffer byteBuffer = ByteBuffer.allocate(NUM_BYTES_IV + encryptedUrlBytes.length + cipher.getBlockSize());
            byteBuffer.put(iv);
            byteBuffer.put(encryptedUrlBytes);
            byteBuffer.put(cipher.getParameters().getEncoded());

            // 返回短链接
            return Base64.getUrlEncoder().withoutPadding().encodeToString(byteBuffer.array()).substring(0, 6);
        }catch (Exception ex){
            log.info("加密失败",ex);
        }

        return null;
    }

    public static String decrypt(String shortUrl)  {
        try{
            // 扩展密钥至指定长度
            byte[] extendedKey = extendKey(BYTE_KEY, KEY_LENGTH);

            // 解析短链接
            byte[] bytes = Base64.getUrlDecoder().decode(shortUrl + "==");
            byte[] iv = new byte[NUM_BYTES_IV];
            byte[] encryptedUrlBytes = new byte[bytes.length - NUM_BYTES_IV - 16];
            byte[] aeadParameters = new byte[16];
            ByteBuffer.wrap(bytes).get(iv);
            ByteBuffer.wrap(bytes, NUM_BYTES_IV, encryptedUrlBytes.length).get(encryptedUrlBytes);
            ByteBuffer.wrap(bytes, NUM_BYTES_IV + encryptedUrlBytes.length, aeadParameters.length).get(aeadParameters);

            // 创建 AEAD 解密器
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            // 初始化解密器
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(extendedKey, "AES"), new IvParameterSpec(iv));

            // 解密 URL
            GCMParameterSpec parameterSpec = new GCMParameterSpec(cipher.getBlockSize() * 8, aeadParameters);
//            byte[] urlBytes = cipher.doFinal(encryptedUrlBytes, 0, encryptedUrlBytes.length - 16, parameterSpec);

            // 验证 URL 鉴别码
//            byte[] expectedMac = new byte[16];
//            System.arraycopy(encryptedUrlBytes, encryptedUrlBytes.length - 16, expectedMac, 0, 16);
//            byte[] mac = CryptoUtil.hmacSha1(urlBytes, extendedKey);
//            if (!Arrays.equals(expectedMac, mac)) {
//                throw new IllegalStateException("Invalid short URL");
//            }

            // 返回 URL
//            return new String(urlBytes, CHARSET_NAME);
        }catch (Exception ex){

            log.info("解密失败",ex);
        }
        return null;
    }



    public static void main(String[] args) {

        try {
            String url = "https://www.baidu.com/";
            String originalUrl = "/api/test?userId=7863784&source=india&phone=18783883778";
            String shortUrl = encrypt(originalUrl);
            // 输出短链接
            System.out.println("Short URL: " + url+shortUrl);
            String decryptedUrl = decrypt(shortUrl);
            // 输出解密后的原始链接
            System.out.println("Decrypted URL: " + decryptedUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
