package com.example.storage.model.request;

import lombok.Data;

/**
 * 创建账户的请求
 * @author Allen
 */
@Data
public class CreateAccountRequest {
    private String accountName;
    private String accountPassword;
}
