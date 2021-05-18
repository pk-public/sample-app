package com.pk.sample.transfer.dsl;

import com.pk.sample.DbTest;
import com.pk.sample.RandomEntityService;
import com.pk.sample.model.Account;
import com.pk.sample.model.Transfer;
import com.pk.sample.model.criteria.Property;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.model.criteria.sentences.EqCondition;
import com.pk.sample.transfer.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DbTest
class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @Autowired
    private RandomEntityService randomEntityService;

    private Account frstAccount;
    private Account scndAccount;

    private Transfer seperateTransaction;
    private List<Transfer> transactions;

    @BeforeEach
    void setUp() {
        frstAccount = randomEntityService.createRandomAccount();
        scndAccount = randomEntityService.createRandomAccount();
        seperateTransaction = randomEntityService.createRandomTransaction(frstAccount, scndAccount);
        transactions = randomEntityService.createRandomTransactions(7, scndAccount, frstAccount);
    }

    @Test
    void shouldFetchBySource() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.SOURCE_ID, frstAccount.getId()))
                .build());

        assertEquals(1, fetched.size());
        assertEquals(fetched.get(0), seperateTransaction);
    }

    @Test
    void shouldFetchByDestination() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.DESTINATION_ID, frstAccount.getId()))
                .build());

        assertEquals(transactions.size(), fetched.size());
        assertThat(fetched, containsInAnyOrder(transactions.toArray()));
    }

    @Test
    void shouldConcatanateConditions() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.DESTINATION_ID, frstAccount.getId()))
                .condition(new EqCondition(Property.SOURCE_ID, frstAccount.getId()))
                .build());

        assertEquals(0, fetched.size());
    }

    @Test
    void shouldNotFetchBySource() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.SOURCE_ID, RandomUtils.nextLong()))
                .build());

        assertEquals(0, fetched.size());
    }

}