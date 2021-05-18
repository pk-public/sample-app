package com.pk.sample.transfer;

import com.pk.sample.DbTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DbTest
@AutoConfigureMockMvc
class TransferControllerValidationTest {

    public static final String EXPECTED_INCORRECT_MAX_AMOUNT_CODE = "maxAmount.incorrect";
    public static final String EXPECTED_INCORRECT_MIN_AMOUNT_CODE = "minAmount.incorrect";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRejectNegativeMinAmount() throws Exception {
        mockMvc.perform(get("/transfer?minAmount=-2"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isString())
                .andExpect(jsonPath("$[0]").value(EXPECTED_INCORRECT_MIN_AMOUNT_CODE));
    }

    @Test
    void shouldRejectNegativeMaxAmount() throws Exception {
        mockMvc.perform(get("/transfer?phrase=10&maxAmount=-7981"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isString())
                .andExpect(jsonPath("$[0]").value(EXPECTED_INCORRECT_MAX_AMOUNT_CODE));
    }

    @Test
    void shouldRejectIncorrectAmountRelation() throws Exception {
        mockMvc.perform(get("/transfer?phrase=10&minAmount=-10&currency=USD&maxAmount=-2"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*]", hasItems(EXPECTED_INCORRECT_MIN_AMOUNT_CODE, EXPECTED_INCORRECT_MAX_AMOUNT_CODE)));
    }

    @Test
    void shouldNotDuplicateErrorCodes() throws Exception {
        mockMvc.perform(get("/transfer?phrase=10&minAmount=-10&currency=USD&maxAmount=-20"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*]", hasItems(EXPECTED_INCORRECT_MIN_AMOUNT_CODE, EXPECTED_INCORRECT_MAX_AMOUNT_CODE)));
    }
}