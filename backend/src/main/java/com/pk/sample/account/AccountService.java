package com.pk.sample.account;

import com.pk.sample.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountDao accountDao;

    @Transactional(readOnly = true)
    public List<Account> listAccounts() {
        return accountDao.fetchAccounts();
    }

    @Transactional
    public Account saveAccount(Account account) {
        return accountDao.saveAccount(account);
    }
}
