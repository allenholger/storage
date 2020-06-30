package com.example.storage.enumeration;

import java.util.Arrays;
import java.util.List;

/**
  *scope 的枚举类型
  *@author: Allen Holger
 * @date: 2020/6/30 15:08
  */
public enum ScopeEnum {
    ALL("ALL", "所有的范围"),
    WEB("web", "web应用"),
    APP("app", "app应用"),
    MICROAPP("micro_app", "小程序");

    /**
     * 授权范围
     */
    private String scope;
    /**
     * 授权范围说明
     */
    private String explain;

    ScopeEnum(String scope, String explain) {
        this.scope = scope;
        this.explain = explain;
    }

    public String getScope() {
        return scope;
    }

    public String getExplain() {
        return explain;
    }

    public static List<ScopeEnum> list(){
        return Arrays.asList(ALL, WEB, APP, MICROAPP);
    }

}
