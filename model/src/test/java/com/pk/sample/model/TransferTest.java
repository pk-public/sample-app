package com.pk.sample.model;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.pk.sample.model.Transfer.newTransfer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TransferTest {

    @Test
    public void shouldCalculateEqualsAndHashCodeForDifferentBDScales() {
        // given
        long seed = RandomUtils.nextLong(0, 2000) - 1000;
        BigDecimal frstBd = BigDecimal.valueOf(seed * 100, 3);
        BigDecimal scndBd = BigDecimal.valueOf(seed, 1);
        assertNotEquals(frstBd, scndBd);
        assertEquals(0, frstBd.compareTo(scndBd));

        // given
        Transfer t1 = createTransfer(frstBd);
        Transfer t2 = createTransfer(scndBd);

        // when
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void shouldCalculateEqualsAndHashCodeForNullAmounts() {
        // given
        Transfer t1 = createTransfer(null);
        Transfer t2 = createTransfer(null);

        // when
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void shouldCalculateEqualsAndHashCodeForSingleNullAmount() {
        // given
        Transfer t1 = createTransfer(BigDecimal.ONE);
        Transfer t2 = createTransfer(null);

        // when
        assertNotEquals(t1, t2);
        assertNotEquals(t1.hashCode(), t2.hashCode());

        assertNotEquals(t2, t1);
        assertNotEquals(t2.hashCode(), t1.hashCode());
    }

    private Transfer createTransfer(BigDecimal frstBd) {
        return newTransfer(1L, 2L, frstBd, Currency.PLN, "t1");
    }

}