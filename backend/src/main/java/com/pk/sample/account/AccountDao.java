package com.pk.sample.account;

import com.pk.sample.db.enums.Currency;
import com.pk.sample.model.Account;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pk.sample.db.Tables.ACCOUNTS;

@Repository
@AllArgsConstructor
public class AccountDao {

    private final DSLContext dsl;

    public List<Account> fetchAccounts() {
        return dsl.selectFrom(ACCOUNTS)
                .orderBy(ACCOUNTS.NAME)
                .fetchInto(Account.class);
    }

    public Account saveAccount(Account account) {
        Long id = dsl.insertInto(ACCOUNTS)
                .set(ACCOUNTS.NAME, account.getName())
                .set(ACCOUNTS.CURRENCY, convertCurrency(account.getCurrency()))
                .returning(ACCOUNTS.ID)
                .fetchOne().getId();
        account.setId(id);
        return account;
    }

    private static Currency convertCurrency(com.pk.sample.model.Currency currency) {
        return Currency.valueOf(currency.name());
    }

}
