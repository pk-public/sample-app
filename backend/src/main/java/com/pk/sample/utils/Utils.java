package com.pk.sample.utils;

import java.math.BigDecimal;

import static java.util.Optional.ofNullable;

public class Utils {

    public static BigDecimal nullableBigDecimal(Double value) {
        return ofNullable(value)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

}
