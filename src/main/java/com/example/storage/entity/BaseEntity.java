package com.example.storage.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 本类是基本实体类， 它是实体类的父类，其他实体类均要继承该类
 * @author allen
 */
@Data
public class BaseEntity<T extends BaseEntity> extends Model{
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建者的ID（对应于用户的ID）
     */
    private Long creator;

    /**
     * 创建的时间
     */
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改者的ID（对应于用户的ID）
     */
    private Long modifier;

    /**
     * 修改的时间
     */
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    private Date modifiedTime;

    /**
     * 数据的有效性，0：有效， 1：无效； 默认数据是有效的
     */
    private Integer validation;

    /**
     * 定义了主键
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
