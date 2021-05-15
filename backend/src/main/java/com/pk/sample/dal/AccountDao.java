package com.pk.sample.dal;

import com.pk.sample.db.tables.records.AccountsRecord;
import com.pk.sample.model.Account;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
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

    public void saveAccount(Account account) {
        Long id = dsl.insertInto(ACCOUNTS)
                .set(ACCOUNTS.NAME, account.getName())
                .returning(ACCOUNTS.ID)
                .fetchOne().getId();
        account.setId(id);
    }

}
