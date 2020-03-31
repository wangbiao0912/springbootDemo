package com.after00.utils.encrypt_and_decode;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/11.
 */
public class RsaGeneratorUtil {
    public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final int MAX_DECRYPT_BLOCK = 128;

    //获得公钥
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        //获得map中的公钥对象 转为key对象
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        //编码返回字符串
        return encryptBASE64(key.getEncoded());
    }

    //获得私钥
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        //获得map中的私钥对象 转为key对象
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        //编码返回字符串
        return encryptBASE64(key.getEncoded());
    }

    //Base64编码解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
        //return Base64.getDecoder().decode(key);
    }

//    //Base64编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
        //return Base64.getEncoder().encodeToString(key);
    }

    //map对象中存放公私钥
    public static Map<String, Object> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 公钥加密
     * @param publicKey 公钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[]  encrypt(RSAPublicKey publicKey, byte[]  plainTextData) throws Exception {
        if (publicKey == null)
            throw new Exception("加密公钥为空");
        if (plainTextData == null || "".equals(plainTextData))
            throw new Exception("明文数据为空");
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥解密过程
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[]  decrypt(RSAPrivateKey privateKey, byte[]  cipherData)  throws Exception {
        if (privateKey == null)
            throw new Exception("解密私钥为空");
        if (cipherData == null || "".equals(cipherData))
            throw new Exception("密文数据为空");
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//            int inputLen = cipherData.length;
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            int offSet = 0;
//            byte[] cache;
//            int i = 0;
//            // 对数据分段解密
//            while (inputLen - offSet > 0) {
//                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//                   cache = cipher.doFinal(cipherData, offSet, MAX_DECRYPT_BLOCK);
//                } else {
//                    cache = cipher.doFinal(cipherData, offSet, inputLen - offSet);
//                }
//                out.write(cache, 0, cache.length);
//                i++;
//                offSet = i * MAX_DECRYPT_BLOCK;
//            }
//            byte[] decryptedData = out.toByteArray();
//            out.close();
//            return decryptedData;
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new BadPaddingException("密文数据已损坏");
        }
    }

    /**
     * 根据字符串生成私钥
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey genPrivateKeyByStr(String privateKeyStr)  throws Exception {
        try {
            byte[] buffer = decryptBASE64(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 根据字符串生成公钥
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey genPublicKeyByStr(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = decryptBASE64(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }
//    public static void main(String[] args) throws Exception {
//        Map<String, Object> keyPairs = RSAUtils.genKeyPair();
//        String privateKey = RSAUtils.getPrivateKey(keyPairs);
//        String publicKey = RSAUtils.getPublicKey(keyPairs);
//
//        System.out.println("私钥：" + privateKey);
//        System.out.println("公钥：" + publicKey);
//
//        // 公钥加密，私钥解密
//        String content = "hello,您好，李四，我喜欢你，这个内容加密了，别人不知道的";
//        byte[] encryptedData = RSAUtils.encryptByPublicKey(content.getBytes(Charset.defaultCharset()), publicKey);
//        byte[] decryptedData = RSAUtils.decryptByPrivateKey(encryptedData, privateKey);
//        System.out.println(new String(decryptedData));
//
//        // 数字签名验证
//        // 私钥签名，生产一个key
//        String sign = RSAUtils.sign(encryptedData, privateKey);
//        // 公钥验证
//        boolean verify = RSAUtils.verify(encryptedData, publicKey, sign);
//        System.out.println("内容是否安全：" + verify);
//
//        // 私钥加密，公钥解密
//        content = "滚你妈的";
//        byte[] encryptedData2 = RSAUtils.encryptByPrivateKey(content.getBytes(Charset.defaultCharset()), privateKey);
//        byte[] decryptedData2 = RSAUtils.decryptByPublicKey(encryptedData2, publicKey);
//        System.out.println(new String(decryptedData2));
//    }
}
