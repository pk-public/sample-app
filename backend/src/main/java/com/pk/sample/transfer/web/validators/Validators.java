package com.pk.sample.transfer.web.validators;

import org.springframework.validation.Errors;

import static com.pk.sample.validation.ValidationErrorCodes.INCORRECT_MAX_AMOUNT;
import static com.pk.sample.validation.ValidationErrorCodes.INCORRECT_MIN_AMOUNT;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;

public class Validators {

    static void validateAmounts(Errors errors, Double minAmount, Double maxAmount) {
        if (nonNull(minAmount) && minAmount < 0)
            errors.rejectValue("minAmount", INCORRECT_MIN_AMOUNT);

        if (nonNull(maxAmount) && maxAmount < 0)
            errors.rejectValue("maxAmount", INCORRECT_MAX_AMOUNT);

        if (allNotNull(minAmount, maxAmount) && maxAmount < minAmount)
            errors.rejectValue("maxAmount", INCORRECT_MAX_AMOUNT);
    }
}
