package com.after00.utils.encrypt_and_decode.symmetry;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密 【DES】
 *
 * @author it
 */
class DESUtil {
    private static final String KEY_ALGORITHM = "DES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";// 默认的加密算法

    /**
     * DES 加密操作
     *
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.getEncoder().encodeToString(result);// 通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(DESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * DES 解密操作
     */
    public static String decrypt(String content, String key) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(DESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // DES 要求密钥长度为 56
            kg.init(56, new SecureRandom(key.getBytes()));

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为DES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String content = "hello,您好，李四，我喜欢你，这个内容加密了，别人不知道的";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        System.out.println("原始内容:" + content);
        String s1 = DESUtil.encrypt(content, key);
        System.out.println("加密后:" + s1);
        System.out.println("解密后:" + DESUtil.decrypt(s1, key));
    }
}
