package com.github.nd.ledger_settlement_engine.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface SettlementService {

    void processTransfer(UUID fromId, UUID toId, BigDecimal amount);
}
