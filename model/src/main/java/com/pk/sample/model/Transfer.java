package com.pk.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(id, transfer.id) &&
                Objects.equals(sourceId, transfer.sourceId) &&
                Objects.equals(destinationId, transfer.destinationId) &&
                isNull(amount) ? isNull(transfer.amount) : nonNull(transfer.amount) && amount.compareTo(transfer.amount) == 0 &&
                currency == transfer.currency &&
                Objects.equals(title, transfer.title);
    }

    @Override
    public int hashCode() {
        Double amountHash = amount == null ? null : amount.doubleValue();
        return Objects.hash(id, sourceId, destinationId, amountHash, currency, title);
    }
}
