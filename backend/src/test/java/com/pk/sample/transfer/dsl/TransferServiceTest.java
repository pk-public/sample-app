package com.pk.sample.transfer.dsl;

import com.pk.sample.DbTest;
import com.pk.sample.RandomEntityTestService;
import com.pk.sample.model.Account;
import com.pk.sample.model.Transfer;
import com.pk.sample.model.criteria.Property;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.model.criteria.sentences.EqCondition;
import com.pk.sample.transfer.TransferService;
import org.apache.commons.lang3.ArrayUtils;
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
    private RandomEntityTestService randomEntityService;

    private Account frstAccount;
    private Account scndAccount;

    private Transfer seperateTransaction;
    private List<Transfer> transactions;
    private Transfer selfReferTransaction;

    @BeforeEach
    void setUp() {
        frstAccount = randomEntityService.createRandomAccount();
        scndAccount = randomEntityService.createRandomAccount();
        seperateTransaction = randomEntityService.createRandomTransaction(frstAccount, scndAccount);
        selfReferTransaction = randomEntityService.createRandomTransaction(frstAccount, frstAccount);
        transactions = randomEntityService.createRandomTransactions(7, scndAccount, frstAccount);
    }

    @Test
    void shouldFetchBySource() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.SOURCE_ID, frstAccount.getId()))
                .build());

        assertEquals(2, fetched.size());
        assertEquals(fetched.get(0), seperateTransaction);
    }

    @Test
    void shouldFetchByDestination() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.DESTINATION_ID, frstAccount.getId()))
                .build());

        Transfer[] expectedTransactions = ArrayUtils.add(transactions.toArray(Transfer[]::new), selfReferTransaction);
        assertEquals(expectedTransactions.length, fetched.size());
        assertThat(fetched, containsInAnyOrder(expectedTransactions));
    }

    @Test
    void shouldConjunctConditions() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.DESTINATION_ID, frstAccount.getId()))
                .condition(new EqCondition(Property.SOURCE_ID, frstAccount.getId()))
                .build());

        assertEquals(1, fetched.size());
    }

    @Test
    void shouldNotFetchBySource() {
        List<Transfer> fetched = transferService.fetch(TransferCriteria.builder()
                .condition(new EqCondition(Property.SOURCE_ID, RandomUtils.nextLong()))
                .build());

        assertEquals(0, fetched.size());
    }

}