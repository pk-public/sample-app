package com.pk.sample.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transfer {

    private Long id;
    private Long sourceId;
    private Long destinationId;
    private BigDecimal amount;
    private String title;


}
