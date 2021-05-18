package com.pk.sample.transfer.web;

import com.pk.sample.model.Currency;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.model.criteria.sentences.InCondition;
import com.pk.sample.model.criteria.sentences.LikeCondition;
import com.pk.sample.model.criteria.sentences.RangeCondition;
import lombok.Data;

import java.util.Set;

import static com.pk.sample.model.criteria.Property.*;
import static com.pk.sample.utils.Consumers.nullableBigDecimal;
import static java.util.Objects.nonNull;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.ObjectUtils.anyNotNull;
import static org.glassfish.jersey.internal.guava.Sets.newHashSet;

@Data
public class ComplexTransferCriteriaDto {

    public Set<Currency> currencies = newHashSet();
    public Double minAmount;
    public Double maxAmount;
    public Set<Long> sourceIds = newHashSet();
    public Set<Long> destinationIds = newHashSet();
    public String phrase;

    public void toTransfer(TransferCriteria.TransferCriteriaBuilder builder) {
        if (isNotEmpty(sourceIds))
            builder.condition(new InCondition(SOURCE_ID, sourceIds));

        if (isNotEmpty(destinationIds))
            builder.condition(new InCondition(DESTINATION_ID, destinationIds));

        if (isNotEmpty(currencies))
            builder.condition(new InCondition(CURRENCY, currencies));

        if (nonNull(phrase))
            builder.condition(new LikeCondition(TITLE, phrase));

        if (anyNotNull(minAmount, maxAmount))
            builder.condition(new RangeCondition(AMOUNT,
                    nullableBigDecimal(minAmount),
                    nullableBigDecimal(maxAmount)));
    }

}
