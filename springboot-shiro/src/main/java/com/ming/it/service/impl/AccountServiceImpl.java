package com.ming.it.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.it.entity.Account;
import com.ming.it.mapper.AccountMapper;
import com.ming.it.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author 11119638
 * @date 2021/6/28 17:17
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public Boolean createAccount(Account account) {
        return save(account);
    }

    @Override
    public Account queryByCode(String code) {
        return getBaseMapper().selectById(code);
    }
}
