package com.github.nd.ledger_settlement_engine.repository;

import com.github.nd.ledger_settlement_engine.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
