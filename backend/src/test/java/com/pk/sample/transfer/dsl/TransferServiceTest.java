package com.pk.sample.transfer.dsl;

import com.pk.sample.DbTest;
import com.pk.sample.account.AccountService;
import com.pk.sample.model.Transfer;
import com.pk.sample.model.criteria.Property;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.model.criteria.sentences.EqCondition;
import com.pk.sample.transfer.TransferService;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static com.pk.sample.model.Account.newAccount;
import static com.pk.sample.model.Currency.USD;
import static com.pk.sample.model.Transfer.newTransfer;
import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils.randomAlphabetic;

@DbTest
class TransferServiceTest {

    @Autowired
    private DSLContext dsl;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransferService transferService;

    private Long frstAccountId;
    private Long scodAccountId;

    @BeforeEach
    void setUp() {
        frstAccountId = generateAccount();
        scodAccountId = generateAccount();
        generateTransfers();
    }

    @Test
    void shouldFetchBySource() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.SOURCE_ID, frstAccountId))
                .build());

        assertEquals(1, fetched.size());
    }

    @Test
    void shouldNotFetchBySource() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.SOURCE_ID, scodAccountId))
                .build());

        assertEquals(0, fetched.size());
    }

    private void generateTransfers() {
        transferService.save(newTransfer(frstAccountId, scodAccountId, TEN, USD, randomAlphabetic(7)));
    }

    private Long generateAccount() {
        return accountService.saveAccount(newAccount(randomAlphabetic(7), USD))
                .getId();
    }
}