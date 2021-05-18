package com.pk.sample.model.criteria;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Value
@Builder
public class TransferCriteria implements Condition {

    @Singular
    public List<Condition> conditions;

    public void accept(ConditionVisitor visitor) {
        conditions.forEach(sentence -> {
            try {
                sentence.accept(visitor);
            } catch (Exception e) {
                log.info("Broken condition {}. Ignoring...", this, e);
            }
        });
    }


}
