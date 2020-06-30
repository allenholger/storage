package com.example.storage.enumeration;

import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
  *授权类型的枚举类
  *@author: Allen Holger
 * @date: 2020/6/30 14:30
  */
public enum GrantTypeEnum {
    PASSWORD("password", "密码"),
    AUTHORIZATIONCODE("authorization_code", "认证码"),
    REFRESHTOKEN("refresh_token", "刷新token"),
    CLIENTCREDENTIALS("client_credentials", "客户端凭证");

    private String grantType;
    //解释、说明
    private String explain;

    GrantTypeEnum(String grantType, String explain) {
        this.grantType = grantType;
        this.explain = explain;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getExplain() {
        return explain;
    }

    /**
     * 将所有的枚举类型生成列表
     * @return
     */
    public static List<GrantTypeEnum> all(){
        return Arrays.asList(PASSWORD, AUTHORIZATIONCODE, REFRESHTOKEN, CLIENTCREDENTIALS);
    }

    /**
     * 将授权类型列表转换成字符串
     * @param list
     * @return
     */
    public static String formatList(List<GrantTypeEnum> list){
        return JSONArray.toJSONString(list.stream().map(GrantTypeEnum::getGrantType).collect(Collectors.toList()));
    }

    /**
     * 将指定的授权类型生成一个列表
     * @param grantTypeEnums
     * @return
     */
    public static List<GrantTypeEnum> list(GrantTypeEnum... grantTypeEnums){
        return Arrays.asList(grantTypeEnums);
    }
}
