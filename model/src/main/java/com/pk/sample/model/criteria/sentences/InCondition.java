package com.pk.sample.model.criteria.sentences;

import com.pk.sample.model.criteria.Property;
import com.pk.sample.model.criteria.Condition;
import com.pk.sample.model.criteria.ConditionVisitor;
import lombok.Data;

import java.util.Set;

@Data
public class InCondition implements Condition {

    private final Property property;
    private final Set<Object> value;

    @Override
    public void accept(ConditionVisitor visitor) {
        visitor.visitInSentence(property, value);
    }

}
