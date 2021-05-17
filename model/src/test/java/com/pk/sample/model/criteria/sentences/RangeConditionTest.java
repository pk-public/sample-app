package com.pk.sample.model.criteria.sentences;

import com.pk.sample.model.criteria.ConditionVisitor;
import com.pk.sample.model.criteria.Property;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.pk.sample.model.criteria.Property.AMOUNT;
import static com.pk.sample.model.criteria.Property.DESTINATION_ID;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RangeConditionTest {

    @Test
    void shouldCreateClosedRange(@Mock ConditionVisitor mockedVisitor) {
        // given
        RangeCondition condition = new RangeCondition(DESTINATION_ID, ONE, TEN);

        // when
        condition.accept(mockedVisitor);

        // then
        verify(mockedVisitor).visitGreaterThenSentence(DESTINATION_ID, ONE);
        verify(mockedVisitor).visitLessThenSentence(DESTINATION_ID, TEN);
        verifyNoMoreInteractions(mockedVisitor);
    }

    @Test
    void shouldCreateOpenRange(@Mock ConditionVisitor mockedVisitor) {
        // given
        RangeCondition condition = new RangeCondition(DESTINATION_ID, ONE, null);

        // when
        condition.accept(mockedVisitor);

        // then
        verify(mockedVisitor).visitGreaterThenSentence(DESTINATION_ID, ONE);
        verifyNoMoreInteractions(mockedVisitor);
    }

    @Test
    void shouldIgnoreRange(@Mock ConditionVisitor mockedVisitor) {
        // given
        RangeCondition condition = new RangeCondition(DESTINATION_ID, null, null);

        // when
        condition.accept(mockedVisitor);

        // then
        verifyNoInteractions(mockedVisitor);
    }
}