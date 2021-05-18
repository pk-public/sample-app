package com.pk.sample.utils;

import com.google.common.collect.ImmutableSet;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class Consumers {

    public static BigDecimal nullableBigDecimal(Double value) {
        return ofNullable(value)
                .map(BigDecimal::valueOf)
                .orElse(null);
    }

    public static <T> void consumeAsSet(T value, Consumer<Set<T>> valueConsumer) {
        ofNullable(value)
                .map(ImmutableSet::of)
                .ifPresent(valueConsumer);
    }

    public static void consumeAsBigDecimal(Double minAmount, Consumer<BigDecimal> minAmount1) {
        ofNullable(minAmount)
                .map(BigDecimal::valueOf)
                .ifPresent(minAmount1);
    }

    public static <T> void consumeIfNotEmpty(Collection<T> collection, Consumer<Collection<T>> consumer) {
        if(isNotEmpty(collection))
            consumer.accept(collection);
    }
}
