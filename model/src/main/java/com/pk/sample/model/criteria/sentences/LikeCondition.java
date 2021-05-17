package com.pk.sample.model.criteria.sentences;

import com.pk.sample.model.criteria.Property;
import com.pk.sample.model.criteria.Condition;
import com.pk.sample.model.criteria.ConditionVisitor;
import lombok.Data;

import java.util.Set;

@Data
public class LikeCondition implements Condition {

    private final Property property;
    private final String phrase;

    @Override
    public void accept(ConditionVisitor visitor) {
        visitor.visitLikeSentence(property, phrase);
    }

}
