package com.zhh.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @Description DES加密解密工具类
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/20 18:51
 */
public class DesUtils {
    private static final String DESUTILS_SECRET ="10B774FB36D8E70B4560A8F0D8634921";

    //加密
    public static byte[] encrypt(String content) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(DESUTILS_SECRET.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content.getBytes());
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public static String decrypt(byte[] content) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(DESUTILS_SECRET.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content);
            return new String(result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

}
