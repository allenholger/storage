package com.example.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.storage.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * 本接口是账户的mapper接口
 * @author allen
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {
}
