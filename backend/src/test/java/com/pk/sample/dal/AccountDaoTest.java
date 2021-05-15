package com.pk.sample.dal;

import com.pk.sample.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DbTest
class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    void shouldUpdateAccountId() {
        // given
        Account account = Account.newAccount(randomAlphanumeric(7));

        // when
        accountDao.saveAccount(account);

        // then
        assertThat(account.getId(), notNullValue());
    }

    @Test
    void shouldFetchStoredAccount() {
        // given
        String name = randomAlphanumeric(7);
        Account account = Account.newAccount(name);

        // when
        accountDao.saveAccount(account);
        List<Account> accounts = accountDao.fetchAccounts();

        // then
        assertThat(accounts, hasSize(1));
        Account expected = accounts.get(0);
        assertEquals(name, account.getName());
        assertThat(expected.getId(), notNullValue());
        assertEquals(expected.getId(), account.getId());
    }
}