package com.pk.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    public static Transfer newTransfer(Long sourceId, Long destinationId, BigDecimal amount, Currency currency, String title) {
        return new Transfer(null, sourceId, destinationId, amount, currency, title);
    }

    private Long id;
    private Long sourceId;
    private Long destinationId;
    private BigDecimal amount;
    private Currency currency;
    private String title;


}
