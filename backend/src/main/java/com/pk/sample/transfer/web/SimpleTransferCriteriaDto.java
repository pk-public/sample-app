package com.pk.sample.transfer.web;

import com.pk.sample.model.Currency;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.model.criteria.sentences.EqCondition;
import com.pk.sample.model.criteria.sentences.LikeCondition;
import com.pk.sample.model.criteria.sentences.RangeCondition;
import lombok.Data;

import static com.pk.sample.model.criteria.Property.*;
import static com.pk.sample.utils.Consumers.nullableBigDecimal;
import static java.util.Objects.nonNull;

@Data
public class SimpleTransferCriteriaDto {

    public Currency currency;
    public Double minAmount;
    public Double maxAmount;
    public Long sourceId;
    public Long destinationId;
    public String phrase;

    public void construct(TransferCriteria.TransferCriteriaBuilder builder) {
        if (nonNull(currency))
            builder.condition(new EqCondition(CURRENCY, currency));

        if (nonNull(sourceId))
            builder.condition(new EqCondition(SOURCE_ID, sourceId));

        if (nonNull(destinationId))
            builder.condition(new EqCondition(DESTINATION_ID, destinationId));

        if (nonNull(phrase))
            builder.condition(new LikeCondition(TITLE, phrase));

        builder.condition(new RangeCondition(AMOUNT,
                nullableBigDecimal(minAmount),
                nullableBigDecimal(maxAmount)));
    }


}
