package com.pk.sample;

import com.pk.sample.model.Account;
import com.pk.sample.model.Currency;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class RandomEntityGenerator {

    public static Account generateAccount() {
        Currency currency = randomCurrency();
        return Account.newAccount(RandomStringUtils.randomAlphabetic(7), currency);
    }

    private static Currency randomCurrency() {
        return Currency.values()[nextInt(0, Currency.values().length)];
    }

}
