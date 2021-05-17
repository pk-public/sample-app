package com.pk.sample.model.criteria;

import java.math.BigDecimal;
import java.util.Set;

public interface ConditionVisitor {

    default void visitEqSentence(Property property, Object value) {
    }

    default void visitInSentence(Property property, Set<?> values) {
    }

    default void visitLikeSentence(Property property, String phrase) {
    }

    default void visitGreaterThenSentence(Property property, BigDecimal value) {
    }

    default void visitLessThenSentence(Property property, BigDecimal value) {
    }

}
