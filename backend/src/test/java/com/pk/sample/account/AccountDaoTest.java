package com.pk.sample.account;

import com.pk.sample.DbTest;
import com.pk.sample.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.pk.sample.RandomEntityGenerator.generateAccount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DbTest
class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    void shouldUpdateEntityAccountId() {
        // given
        Account account = generateAccount();

        // when
        accountDao.saveAccount(account);

        // then
        assertThat(account.getId(), notNullValue());
    }

    @Test
    void shouldFetchStoredAccount() {
        // given
        Account account = generateAccount();

        // when
        accountDao.saveAccount(account);
        List<Account> accounts = accountDao.fetchAccounts();

        // then
        assertThat(account.getId(), notNullValue());

        assertThat(accounts, hasSize(1));
        Account result = accounts.get(0);
        assertThat(result, is(account));
        assertEquals(result.getId(), account.getId());
    }

    @Test
    void shouldFetchNotOverwriteStoredAccount() {
        // given
        Account account = generateAccount();

        // when
        shouldFetchStoredAccount();
        accountDao.saveAccount(account);
        List<Account> accounts = accountDao.fetchAccounts();

        // then
        assertThat(accounts, hasSize(2));
        assertThat(accounts, hasItem(account));
    }
}