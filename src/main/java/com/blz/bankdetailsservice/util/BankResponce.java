package com.blz.bankdetailsservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankResponce {
    private int errorcode;
    private String message;
    private Object token;
}
