package com.pk.sample.transfer;

import com.pk.sample.model.Transfer;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.transfer.web.ComplexTransferCriteriaDto;
import com.pk.sample.transfer.web.SimpleTransferCriteriaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<Transfer> fetchSimple(@Valid SimpleTransferCriteriaDto criteriaDto) {
        return fetch(criteriaDto::construct);
    }

    @GetMapping("/complex")
    public List<Transfer> fetchComplex(@Valid ComplexTransferCriteriaDto criteriaDto) {
        return fetch(criteriaDto::construct);
    }

    private List<Transfer> fetch(Consumer<TransferCriteria.TransferCriteriaBuilder> criteriaConstructor) {
        TransferCriteria.TransferCriteriaBuilder builder = TransferCriteria.builder();
        criteriaConstructor.accept(builder);
        return transferService.fetch(builder.build());

    }

}
