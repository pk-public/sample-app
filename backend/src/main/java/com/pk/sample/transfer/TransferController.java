package com.pk.sample.transfer;

import com.pk.sample.model.Transfer;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.transfer.web.ComplexTransferCriteriaDto;
import com.pk.sample.transfer.web.SimpleTransferCriteriaDto;
import com.pk.sample.transfer.web.validators.SimpleTransferCriteriaDtoValidator;
import com.pk.sample.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("/transfer")
@AllArgsConstructor
public class TransferController {

    private final TransferService transferService;
    private final SimpleTransferCriteriaDtoValidator simpleTransferCriteriaDtoValidator;


    @InitBinder("simpleTransferCriteriaDto")
    public void initReservationValidator(WebDataBinder binder) {
        binder.addValidators(simpleTransferCriteriaDtoValidator);
    }

    @GetMapping
    public List<Transfer> fetchSimple(@Valid SimpleTransferCriteriaDto criteriaDto, Errors errors) {
        if(errors.hasErrors())
            throw new ValidationException(errors.getAllErrors());

        return fetch(criteriaDto::toTransfer);
    }

    @GetMapping("/complex")
    public List<Transfer> fetchComplex(@Valid ComplexTransferCriteriaDto criteriaDto, Errors errors) {
        if(errors.hasErrors())
            throw new ValidationException(errors.getAllErrors());

        return fetch(criteriaDto::toTransfer);
    }

    private List<Transfer> fetch(Consumer<TransferCriteria.TransferCriteriaBuilder> criteriaConstructor) {
        TransferCriteria.TransferCriteriaBuilder builder = TransferCriteria.builder();
        criteriaConstructor.accept(builder);
        return transferService.fetch(builder.build());

    }

}
