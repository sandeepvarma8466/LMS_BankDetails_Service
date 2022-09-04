package com.blz.bankdetailsservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BankNotFoundException extends RuntimeException {
    private int statuscode;
    private String message;

    public BankNotFoundException(String message, int statuscode) {
        super(message);
        this.statuscode = statuscode;
        this.message = message;
    }
}
