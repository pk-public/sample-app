package com.pk.sample.model.criteria.sentences;

import com.pk.sample.model.criteria.Property;
import com.pk.sample.model.criteria.Condition;
import com.pk.sample.model.criteria.ConditionVisitor;
import lombok.Data;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Data
public class RangeCondition implements Condition {

    private final Property property;
    private final BigDecimal minValue;
    private final BigDecimal maxValue;

    @Override
    public void accept(ConditionVisitor visitor) {
        if (nonNull(minValue))
            visitor.visitGreaterThenSentence(property, minValue);

        if (nonNull(maxValue))
            visitor.visitLessThenSentence(property, maxValue);
    }

}
