package com.blz.bankdetailsservice.controller;

import com.blz.bankdetailsservice.dto.BankDetailsDTO;
import com.blz.bankdetailsservice.model.BankDetailsModel;
import com.blz.bankdetailsservice.service.IBankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bankdetails")
public class BankDetailsController {
    @Autowired
    IBankDetailsService bankDetailsService;

    @PostMapping("/addbank")
    public BankDetailsModel inserBank(@Valid @RequestBody BankDetailsDTO bankDetailsDTO, @RequestHeader String token) {
        return bankDetailsService.insertBank(bankDetailsDTO, token);
    }

    @PutMapping("/updatebankby/{id}")
    public BankDetailsModel updateBank(@PathVariable("id") Long id, @Valid @RequestBody BankDetailsDTO bankDetailsDTO,
                                       @RequestHeader String token) {
        return bankDetailsService.updateBank(id, bankDetailsDTO, token);
    }

    @GetMapping("/fetchbankdetails")
    public List<BankDetailsModel> getAllBankDetails(@RequestHeader String token) {
        return bankDetailsService.getAllBankDetails(token);
    }

    @GetMapping("/fetchdetailsby/{id}")
    public BankDetailsModel getBankById(@PathVariable("id") Long id, @RequestHeader String token) {
        return bankDetailsService.getBankById(id, token);
    }

    @DeleteMapping("/deletebank/{id}")
    public BankDetailsModel deleteBank(@PathVariable("id") Long id, @RequestHeader String token) {
        return bankDetailsService.deleteBank(id, token);
    }
}
