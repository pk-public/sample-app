package com.pk.sample.transfer;

import com.pk.sample.DbTest;
import com.pk.sample.RandomEntityService;
import com.pk.sample.model.Account;
import com.pk.sample.model.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DbTest
@AutoConfigureMockMvc
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RandomEntityService randomEntityService;

    private Account frstAccount;
    private Account scndAccount;
    private List<Transfer> firstBatch;
    private List<Transfer> scndBatch;

    @BeforeEach
    void setUp() {
        frstAccount = randomEntityService.createRandomAccount();
        scndAccount = randomEntityService.createRandomAccount();
        firstBatch = randomEntityService.createRandomTransactions(50, frstAccount, scndAccount);
        scndBatch = randomEntityService.createRandomTransactions(25, scndAccount, frstAccount);
    }

    @Test
    void shouldFetchBySourceId() throws Exception {
        // todo: compare data itself
        mockMvc.perform(get("/transfer?sourceId={sourceId}", frstAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(firstBatch.size())));
    }

    @Test
    void shouldFetchByDestinationId() throws Exception {
        // todo: compare data itself
        mockMvc.perform(get("/transfer?destinationId={destinationId}", frstAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(scndBatch.size())));
    }

}