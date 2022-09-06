package com.blz.bankdetailsservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class BankDetailsDTO {
    //@NotNull(message = "accountnumber cannot be empty")
    private long accountNo;
    //@NotNull(message = "ifsc code cannot be empty")
    private String ifscCode;
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "branch name invalid")
    private String branch;
    @Pattern(regexp = "^[1-9]{2}\\s{1}[1-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
    private long linkedMobNo;
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "account holder name invalid")
    private String accountHolderName;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email Id")
    private String emailId;
}
