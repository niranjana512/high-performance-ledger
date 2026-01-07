package com.github.nd.ledger_settlement_engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> ledgerException(LedgerException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDate.now(),
                        "message", ex.getMessage(),
                        "status", HttpStatus.BAD_REQUEST.value()
                ), HttpStatus.BAD_REQUEST
        );
    }

}
