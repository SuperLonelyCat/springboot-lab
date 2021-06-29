package com.ming.it.service;

import com.ming.it.entity.Account;

/**
 * @author 11119638
 * @date 2021/6/28 17:14
 */
public interface AccountService {

    Boolean createAccount(Account account);

    Account queryByCode(String code);
}
