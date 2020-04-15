package com.example.storage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 账户的实体类
 * @author allen
 */
@Data
@TableName("t_account")
public class Account extends BaseEntity<Account>{
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 账户密码
     */
    private String accountPassword;
    /**
     * 盐值（由系统生成的64位随机字符串，用来给密码加密）
     */
    private String salt;

}
