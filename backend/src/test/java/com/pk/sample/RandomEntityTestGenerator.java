package com.pk.sample;

import com.pk.sample.model.Account;
import com.pk.sample.model.Currency;
import com.pk.sample.model.Transfer;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;

import static com.pk.sample.model.Transfer.newTransfer;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils.randomAlphabetic;

public class RandomEntityTestGenerator {

    public static Transfer generateTransfer(Account source, Account destination) {
        return newTransfer(source.getId(), destination.getId(), BigDecimal.valueOf(nextLong(1, 999_999)), source.getCurrency(), randomAlphabetic(7));
    }

    public static Account generateAccount() {
        Currency currency = randomCurrency();
        return Account.newAccount(RandomStringUtils.randomAlphabetic(7), currency);
    }

    private static Currency randomCurrency() {
        return Currency.values()[nextInt(0, Currency.values().length)];
    }

}
