package com.pk.sample.model.criteria;


public interface Condition {

    void accept(ConditionVisitor visitor);
}
