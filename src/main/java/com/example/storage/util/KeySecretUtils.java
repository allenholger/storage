package com.example.storage.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
  * AppKey 和appSecret的工具类
  *@author: Allen Holger
 * @date: 2020/6/10 21:33
  */
public class KeySecretUtils {

    /**
     *生成AppKey 该appKey是一个UUID生成的字符串
     * @return
     */
    public static String generateAppKey(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 根据appKey以及clientId, 使用sha1的算法，生成appSecret
     * @param appKey
     * @param clientId
     * @return
     */
    public static String generateAppSecret(String appKey, String clientId){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(appKey.concat(clientId).getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < digest.length; i++){
                String shaHex = Integer.toHexString(digest[i] & 0XFF);
                if(shaHex.length() < 2){
                    sb.append(0);
                }
                sb.append(shaHex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
