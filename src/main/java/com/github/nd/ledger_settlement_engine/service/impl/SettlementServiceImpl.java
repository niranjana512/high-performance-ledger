package com.github.nd.ledger_settlement_engine.service.impl;

import com.github.nd.ledger_settlement_engine.exception.LedgerException;
import com.github.nd.ledger_settlement_engine.repository.AccountRepository;
import com.github.nd.ledger_settlement_engine.service.SettlementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SettlementServiceImpl implements SettlementService {

    private final AccountRepository accountRepository;

    public SettlementServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void processTransfer(UUID fromId, UUID toId, BigDecimal amount) {
        var fromAccount = accountRepository.findById(fromId)
                .orElseThrow(() -> new LedgerException("Sender not found."));
        var toAccount = accountRepository.findById(toId)
                .orElseThrow(() -> new LedgerException("Receiver not found."));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new LedgerException("Transaction failed: Insufficient funds in account : " + fromId);
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        //Optimistic locking happens automatically here
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

}
