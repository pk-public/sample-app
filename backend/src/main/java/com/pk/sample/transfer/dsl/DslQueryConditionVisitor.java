package com.pk.sample.transfer.dsl;

import com.pk.sample.db.tables.records.TransfersRecord;
import com.pk.sample.model.criteria.ConditionVisitor;
import com.pk.sample.model.criteria.Property;
import org.jooq.Field;
import org.jooq.SelectConditionStep;

import java.math.BigDecimal;
import java.util.Set;

import static java.lang.String.format;

class DslQueryConditionVisitor implements ConditionVisitor {

    private final PropertyDslTranslator translator;
    private SelectConditionStep<TransfersRecord> query;

    public DslQueryConditionVisitor(PropertyDslTranslator translator, SelectConditionStep<TransfersRecord> query) {
        this.translator = translator;
        this.query = query;
    }

    @Override
    public void visitEqSentence(Property property, Object value) {
        Field<?> field = translator.translate(property);
        query = query.and(format("%s = ?", field.getQualifiedName()), value);
    }

    @Override
    public void visitInSentence(Property property, Set<?> values) {
        Field<?> field = translator.translate(property);
        query = query.and(field.in(values));
    }

    @Override
    public void visitLikeSentence(Property property, String phrase) {
        Field<?> field = translator.translate(property);
        query = query.and(field.like(format("%%%s%%", phrase)));
    }

    @Override
    public void visitGreaterThenSentence(Property property, BigDecimal value) {
        Field<?> field = translator.translate(property);
        query = query.and(format("%s > ?", field.getQualifiedName()), value);
    }

    @Override
    public void visitLessThenSentence(Property property, BigDecimal value) {
        Field<?> field = translator.translate(property);
        query = query.and(format("%s < ?", field.getQualifiedName()), value);
    }

    public SelectConditionStep<TransfersRecord> getQuery() {
        return query;
    }
}
