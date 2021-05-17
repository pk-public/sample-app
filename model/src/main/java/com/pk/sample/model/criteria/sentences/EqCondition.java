package com.pk.sample.model.criteria.sentences;

import com.pk.sample.model.criteria.Property;
import com.pk.sample.model.criteria.Condition;
import com.pk.sample.model.criteria.ConditionVisitor;
import lombok.Data;

@Data
public class EqCondition implements Condition {

    private final Property property;
    private final Object value;

    @Override
    public void accept(ConditionVisitor visitor) {
        visitor.visitEqSentence(property, value);
    }

}
