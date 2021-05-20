package com.pk.sample.model.criteria;

import com.pk.sample.model.criteria.sentences.EqCondition;
import com.pk.sample.model.criteria.sentences.LikeCondition;
import com.pk.sample.model.criteria.sentences.RangeCondition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.pk.sample.model.criteria.Property.*;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferCriteriaTest {

    @Test
    void shouldNotVisitEmptyCondition(@Mock ConditionVisitor mockedVisitor) {
        // given
        TransferCriteria criteria = TransferCriteria.builder()
                .build();

        // when
        criteria.accept(mockedVisitor);

        // verify
        verifyNoInteractions(mockedVisitor);
    }

    @Test
    void shouldVisitLikeCondition(@Mock ConditionVisitor mockedVisitor) {
        // given
        TransferCriteria criteria = TransferCriteria.builder()
                .condition(new LikeCondition(TITLE, "a"))
                .build();

        // when
        criteria.accept(mockedVisitor);

        // verify
        verify(mockedVisitor).visitLikeSentence(TITLE, "a");
        verifyNoMoreInteractions(mockedVisitor);
    }

    @Test
    void shouldVisitRangeCondition(@Mock ConditionVisitor mockedVisitor) {
        // given
        TransferCriteria criteria = TransferCriteria.builder()
                .condition(new RangeCondition(AMOUNT, ONE, TEN))
                .build();

        // when
        criteria.accept(mockedVisitor);

        // verify
        verify(mockedVisitor).visitGreaterThenSentence(AMOUNT, ONE);
        verify(mockedVisitor).visitLessThenSentence(AMOUNT, TEN);
        verifyNoMoreInteractions(mockedVisitor);
    }

    @Test
    void shouldVisitManyConditions(@Mock ConditionVisitor mockedVisitor) {
        // given
        TransferCriteria criteria = TransferCriteria.builder()
                .condition(new LikeCondition(TITLE, "a"))
                .condition(new EqCondition(SOURCE_ID, 1L))
                .condition(new RangeCondition(AMOUNT, ONE, TEN))
                .build();

        // when
        criteria.accept(mockedVisitor);

        // verify
        verify(mockedVisitor).visitLikeSentence(TITLE, "a");
        verify(mockedVisitor).visitEqSentence(SOURCE_ID, 1L);
        verify(mockedVisitor).visitGreaterThenSentence(AMOUNT, ONE);
        verify(mockedVisitor).visitLessThenSentence(AMOUNT, TEN);
        verifyNoMoreInteractions(mockedVisitor);
    }

    @Test
    void shouldIgnoreBrokenConditions(@Mock ConditionVisitor mockedVisitor) {
        // given
        doThrow(new RuntimeException())
                .when(mockedVisitor)
                .visitEqSentence(any(), any());

        // when
        shouldVisitManyConditions(mockedVisitor);

        // then no exceptions should be thrown
    }


}