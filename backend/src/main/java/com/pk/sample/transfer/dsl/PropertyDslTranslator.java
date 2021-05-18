package com.pk.sample.transfer.dsl;

import com.google.common.collect.ImmutableMap;
import com.pk.sample.model.criteria.Property;
import org.apache.commons.lang3.NotImplementedException;
import org.jooq.Field;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

import static com.pk.sample.db.tables.Transfers.TRANSFERS;
import static com.pk.sample.model.criteria.Property.*;
import static java.lang.String.format;

@Component
public class PropertyDslTranslator {

    private final static EnumMap<Property, Field<?>> PROPERITES = new EnumMap<>(ImmutableMap.<Property, Field<?>>of(
            TITLE, TRANSFERS.TITLE,
            AMOUNT, TRANSFERS.AMOUNT,
            CURRENCY, TRANSFERS.CURRENCY,
            SOURCE_ID, TRANSFERS.SOURCE_ID,
            DESTINATION_ID, TRANSFERS.DESTINATION_ID
    ));

    public Field<?> translate(Property property) {
        if (!PROPERITES.containsKey(property))
            throw new NotImplementedException(format("Property[%s] not yet implemented on DSL layer", property));

        return PROPERITES.get(property);
    }

}
