package com.pk.sample;

import com.pk.sample.account.AccountService;
import com.pk.sample.model.Account;
import com.pk.sample.model.Transfer;
import com.pk.sample.transfer.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static com.pk.sample.RandomEntityGenerator.generateAccount;
import static com.pk.sample.RandomEntityGenerator.generateTransfer;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class RandomEntityService {

    private final AccountService accountService;
    private final TransferService transferService;

    public Account createRandomAccount() {
        return accountService.saveAccount(generateAccount());
    }

    public List<Transfer> createRandomTransactions(int count, Account source, Account destination) {
        return Stream.generate(() -> generateTransfer(source, destination))
                .limit(count)
                .map(transferService::save)
                .collect(toList());
    }

    public Transfer createRandomTransaction(Account source, Account destination) {
        return transferService.save(generateTransfer(source, destination));
    }

}
