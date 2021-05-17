package com.pk.sample.model.criteria;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TransferCriteria implements Condition {

    @Singular
    public List<Condition> conditions;

    public void accept(ConditionVisitor visitor) {
        conditions.forEach(sentence -> sentence.accept(visitor));
    }


}
