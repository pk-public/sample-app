package com.pk.sample.transfer.web.validators;

import com.pk.sample.transfer.web.SimpleTransferCriteriaDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.pk.sample.transfer.web.validators.Validators.validateAmounts;

@Component
public class SimpleTransferCriteriaDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return SimpleTransferCriteriaDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SimpleTransferCriteriaDto dto = (SimpleTransferCriteriaDto) o;

        validateAmounts(errors, dto.getMinAmount(), dto.getMaxAmount());
    }

}
