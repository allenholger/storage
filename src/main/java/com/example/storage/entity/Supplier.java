package com.example.storage.entity;

import lombok.Data;

/**
 * 本类是供货商的实体类
 * @author Allen
 */
@Data
public class Supplier extends BaseEntity<Supplier>{
    /**
     * 供货商的性名
     */
    private String supplierName;
    /**
     * 供货商地址
     */
    private String supplierAddress;

    /**
     *供货商联系人
     */
    private String supplierContact;

    /**
     * 供货商联系电话
     */
    private String supplierTelephone;

}
