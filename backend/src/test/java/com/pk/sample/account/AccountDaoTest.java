package com.pk.sample.account;

import com.pk.sample.DbTest;
import com.pk.sample.model.Account;
import com.pk.sample.model.Currency;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.pk.sample.model.Currency.PLN;
import static com.pk.sample.model.Currency.USD;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DbTest
class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    void shouldUpdateAccountId() {
        // given
        Account account = Account.newAccount(randomAlphanumeric(7), PLN);

        // when
        accountDao.saveAccount(account);

        // then
        assertThat(account.getId(), Matchers.notNullValue());
    }

    @Test
    void shouldFetchStoredAccount() {
        // given
        String name = randomAlphanumeric(7);
        Currency currency = USD;
        Account account = Account.newAccount(name, currency);

        // when
        accountDao.saveAccount(account);
        List<Account> accounts = accountDao.fetchAccounts();

        // then
        assertThat(accounts, hasSize(1));
        Account expected = accounts.get(0);
        assertEquals(name, account.getName());
        assertEquals(currency, account.getCurrency());
        assertThat(expected.getId(), Matchers.notNullValue());
        assertEquals(expected.getId(), account.getId());
    }
}