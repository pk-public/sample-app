package com.pk.sample.transfer.web;

import com.pk.sample.model.Currency;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.model.criteria.sentences.EqCondition;
import com.pk.sample.model.criteria.sentences.LikeCondition;
import com.pk.sample.model.criteria.sentences.RangeCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.pk.sample.model.criteria.Property.*;
import static com.pk.sample.utils.Utils.nullableBigDecimal;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.anyNotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTransferCriteriaDto {

    public Currency currency;
    public Double minAmount;
    public Double maxAmount;
    public Long sourceId;
    public Long destinationId;
    public String phrase;

    public void toTransfer(TransferCriteria.TransferCriteriaBuilder builder) {
        if (nonNull(currency))
            builder.condition(new EqCondition(CURRENCY, currency));

        if (nonNull(sourceId))
            builder.condition(new EqCondition(SOURCE_ID, sourceId));

        if (nonNull(destinationId))
            builder.condition(new EqCondition(DESTINATION_ID, destinationId));

        if (nonNull(phrase))
            builder.condition(new LikeCondition(TITLE, phrase));

        if (anyNotNull(minAmount, maxAmount))
            builder.condition(new RangeCondition(AMOUNT,
                    nullableBigDecimal(minAmount),
                    nullableBigDecimal(maxAmount)));
    }


}
