package com.pk.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    public static Account newAccount(String name, Currency currency) {
        return new Account(null, name, currency);
    }

    private Long id;
    private String name;
    private Currency currency;

}
