package com.pk.sample.transfer;

import com.pk.sample.model.Transfer;
import com.pk.sample.model.criteria.TransferCriteria;
import com.pk.sample.transfer.dsl.TransferDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TransferService {

    private final TransferDao transferDao;

    @Transactional(readOnly = true)
    public List<Transfer> fetch(TransferCriteria transferCriteria) {
        log.info("Fetching transfers: {}", transferCriteria);
        return transferDao.fetch(transferCriteria);
    }

    @Transactional
    public Transfer save(Transfer transfer) {
        return transferDao.save(transfer);
    }
}
