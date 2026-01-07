package com.github.nd.ledger_settlement_engine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name="accounts")
public class Account {

    private UUID id;
    private String userName;
    private BigDecimal balance;

    @Version
    private Long version;
}
