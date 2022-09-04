package com.blz.bankdetailsservice.exception.exceptionhandler;

import com.blz.bankdetailsservice.exception.BankNotFoundException;
import com.blz.bankdetailsservice.util.BankResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BankDetailExceptionHandler {
    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<BankResponce> response(BankNotFoundException exception) {
        BankResponce bankResponse = new BankResponce();
        bankResponse.setErrorcode(400);
        bankResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(bankResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BankResponce>
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        BankResponce bankResponse = new BankResponce();
        bankResponse.setErrorcode(500);
        bankResponse.setMessage(e.getMessage());
        return new ResponseEntity<BankResponce>(bankResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
