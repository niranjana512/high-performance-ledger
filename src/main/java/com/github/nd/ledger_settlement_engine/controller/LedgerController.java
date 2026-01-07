package com.github.nd.ledger_settlement_engine.controller;

import com.github.nd.ledger_settlement_engine.service.SettlementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

    private final SettlementService settlementService;

    public LedgerController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping("/transfer")
    public String transferFunds(@RequestParam UUID fromId, @RequestParam UUID toId,
                                @RequestParam BigDecimal amount) {
        settlementService.processTransfer(fromId, toId, amount);
        return "Transaction Successful";
    }
}
