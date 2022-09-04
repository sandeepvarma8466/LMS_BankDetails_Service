package com.blz.bankdetailsservice.service;

import com.blz.bankdetailsservice.dto.BankDetailsDTO;
import com.blz.bankdetailsservice.model.BankDetailsModel;

import java.util.List;

public interface IBankDetailsService {
    BankDetailsModel insertBank(BankDetailsDTO bankDetailsDTO, String token);

    BankDetailsModel updateBank(Long id, BankDetailsDTO bankDetailsDTO, String token);

    List<BankDetailsModel> getAllBankDetails(String token);

    BankDetailsModel getBankById(Long id, String token);

    BankDetailsModel deleteBank(Long id, String token);
}
