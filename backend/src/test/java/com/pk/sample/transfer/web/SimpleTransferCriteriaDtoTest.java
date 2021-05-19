package com.pk.sample.transfer.web;

import com.pk.sample.model.criteria.Condition;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.model.criteria.sentences.EqCondition;
import com.pk.sample.model.criteria.sentences.LikeCondition;
import com.pk.sample.model.criteria.sentences.RangeCondition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.pk.sample.RandomEntityTestGenerator.randomCurrency;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleTransferCriteriaDtoTest {

    @Test
    void shouldConvertAllCondtions() {
        // given
        SimpleTransferCriteriaDto dto = new SimpleTransferCriteriaDto(randomCurrency(), nextDouble(1, 10), nextDouble(10, 20), nextLong(1, 10), nextLong(1, 10), "qwer");

        // when
        TransferCriteria.TransferCriteriaBuilder builder = TransferCriteria.builder();
        dto.toTransfer(builder);
        TransferCriteria criteria = builder.build();

        // then
        List<Condition> conditions = criteria.getConditions();
        assertEquals(conditions.size(), 5);
        assertThat(conditions, containsInAnyOrder(isA(EqCondition.class), isA(EqCondition.class), isA(EqCondition.class), isA(RangeCondition.class), isA(LikeCondition.class)));
    }

    @Test
    void shouldConvertConvertNoCondtions() {
        // given
        SimpleTransferCriteriaDto dto = new SimpleTransferCriteriaDto(null, null, null, null, null, null);

        // when
        TransferCriteria.TransferCriteriaBuilder builder = TransferCriteria.builder();
        dto.toTransfer(builder);
        TransferCriteria criteria = builder.build();

        // then
        List<Condition> conditions = criteria.getConditions();
        assertEquals(conditions.size(), 0);
    }

    @Test
    void shouldConvertRangeCondtion() {
        // given
        SimpleTransferCriteriaDto dto = new SimpleTransferCriteriaDto(null, 1d, 10d, null, null, null);

        // when
        TransferCriteria.TransferCriteriaBuilder builder = TransferCriteria.builder();
        dto.toTransfer(builder);
        TransferCriteria criteria = builder.build();

        // then
        List<Condition> conditions = criteria.getConditions();
        assertEquals(conditions.size(), 1);
        Condition given = conditions.get(0);
        assertThat(given, isA(RangeCondition.class));
        RangeCondition range = (RangeCondition) given;
        assertEquals(0, ONE.compareTo(range.getMinValue()));
        assertEquals(0, TEN.compareTo(range.getMaxValue()));
    }
}