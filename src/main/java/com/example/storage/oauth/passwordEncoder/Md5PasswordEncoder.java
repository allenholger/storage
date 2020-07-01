package com.example.storage.oauth.passwordEncoder;

import com.example.storage.util.PasswordUtils;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
  * 基于md5d的密码加密
  *@author: Allen Holger
 * @date: 2020/6/26 20:53
  */

//public class Md5PasswordEncoder implements PasswordEncoder {
//
//    private String salt;
//
//    public Md5PasswordEncoder(String salt) {
//        this.salt = salt;
//    }
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return PasswordUtils.encrypt(String.valueOf(rawPassword), salt);
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return PasswordUtils.isMatch(String.valueOf(rawPassword), salt, encodedPassword);
//    }
//
//    @Override
//    public boolean upgradeEncoding(String encodedPassword) {
//        return false;
//    }
//}
