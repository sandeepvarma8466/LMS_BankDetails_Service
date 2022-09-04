package com.blz.bankdetailsservice.model;

import com.blz.bankdetailsservice.dto.BankDetailsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_details")
@Data
@NoArgsConstructor
public class BankDetailsModel {
    @Id
    @GenericGenerator(name = "bank_details", strategy = "increment")
    @GeneratedValue(generator = "bank_details")
    private long id;
    private long accountNo;
    private String ifscCode;
    private String branch;
    private long linkedMobNo;
    private String accountHolderName;
    private String emailId;
    private String creatorUser;
    private String updatedUser;
    private LocalDateTime creatorDateTime;
    private LocalDateTime updatedDateTime;

    public BankDetailsModel(BankDetailsDTO bankDetailsDTO) {
        this.accountNo = bankDetailsDTO.getAccountNo();
        this.ifscCode = bankDetailsDTO.getIfscCode();
        this.branch = bankDetailsDTO.getBranch();
        this.linkedMobNo = bankDetailsDTO.getLinkedMobNo();
        this.accountHolderName = bankDetailsDTO.getAccountHolderName();
        this.emailId = bankDetailsDTO.getEmailId();
        this.creatorDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
    }
}
