package com.pk.sample.transfer.web.validators;

import com.pk.sample.transfer.web.ComplexTransferCriteriaDto;
import com.pk.sample.transfer.web.SimpleTransferCriteriaDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.pk.sample.transfer.web.validators.Validators.validateAmounts;

@Component
public class ComplexTransferCriteriaDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ComplexTransferCriteriaDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ComplexTransferCriteriaDto dto = (ComplexTransferCriteriaDto) o;

        validateAmounts(errors, dto.getMinAmount(), dto.getMaxAmount());
    }

}
