package com.pk.sample.transfer.dsl;

import com.pk.sample.db.enums.Currency;
import com.pk.sample.db.tables.records.TransfersRecord;
import com.pk.sample.model.Transfer;
import com.pk.sample.model.criteria.TransferCriteria;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.jooq.SelectWhereStep;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pk.sample.db.Tables.TRANSFERS;
import static org.jooq.impl.DSL.noCondition;

@Repository
@AllArgsConstructor
public class TransferDao {

    private final DSLContext dsl;
    private final PropertyDslTranslator translator;


    public List<Transfer> fetch(TransferCriteria transferCriteria) {

        SelectWhereStep<TransfersRecord> select = dsl.selectFrom(TRANSFERS);
        SelectConditionStep<TransfersRecord> query = select.where(noCondition());

        DslQueryConditionVisitor visitor = new DslQueryConditionVisitor(translator, query);
        transferCriteria.accept(visitor);

        return visitor.getQuery().fetchInto(Transfer.class);
    }

    public Transfer save(Transfer transfer) {
        Long id = dsl.insertInto(TRANSFERS)
                .set(TRANSFERS.SOURCE_ID, transfer.getSourceId())
                .set(TRANSFERS.DESTINATION_ID, transfer.getDestinationId())
                .set(TRANSFERS.AMOUNT, transfer.getAmount())
                .set(TRANSFERS.CURRENCY, Currency.valueOf(transfer.getCurrency().name()))
                .set(TRANSFERS.TITLE, transfer.getTitle())
                .returning(TRANSFERS.ID)
                .fetchOne().getId();
        transfer.setId(id);
        return transfer;
    }
}
