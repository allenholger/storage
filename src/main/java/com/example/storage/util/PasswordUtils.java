package com.example.storage.util;

import org.springframework.util.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * 本类是用户登陆密码的工具类
 * @author allen
 */
public class PasswordUtils {
    /**
     * 生成盐值
     * @return
     */
    public static String generateSalt(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 本方法是生成随机密码
     * @param pwdLength   密码的长度
     * @return
     */
    public static String generateRandomPassword(Integer pwdLength){
        char[] charPool = new char[]{'A','B', 'C',  'D', 'E', 'F', 'G', 'H', 'I','J','K', 'L','M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '@', '$', '#', '%', '&'
        };
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pwdLength; i++){
            sb.append(charPool[new Random().nextInt(charPool.length)]);
        }
        return sb.toString();
    }

    /**
     * 对用户的登陆密码进行加密,该方法采用的是spring的内置方法
     * @param password
     * @param salt
     * @return
     */
    public static String encrypt(String password, String salt){
        return DigestUtils.md5DigestAsHex(mixPasswordAndSalt(password, salt).getBytes());
    }

    /**
     * 判断密码是否和存储的密码一致
     * @param password
     * @param salt
     * @param storePassword
     * @return
     */
    public static boolean isMatch(String password, String salt, String storePassword){
        return DigestUtils.md5DigestAsHex(mixPasswordAndSalt(password, salt).getBytes()).equals(storePassword);
    }

    /**
     * 混合密码和盐值
     * @param password     密码
     * @param salt         盐值
     * @return
     */
    private static String mixPasswordAndSalt(String password, String salt){
        return password.concat(salt);
    }
}
