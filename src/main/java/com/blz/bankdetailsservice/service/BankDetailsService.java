package com.blz.bankdetailsservice.service;

import com.blz.bankdetailsservice.dto.BankDetailsDTO;
import com.blz.bankdetailsservice.exception.BankNotFoundException;
import com.blz.bankdetailsservice.model.BankDetailsModel;
import com.blz.bankdetailsservice.repository.BankDetailsRepository;
import com.blz.bankdetailsservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankDetailsService implements IBankDetailsService {
    @Autowired
    BankDetailsRepository bankDetailsRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public BankDetailsModel insertBank(BankDetailsDTO bankDetailsDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
        BankDetailsModel bankDetailsModel = new BankDetailsModel(bankDetailsDTO);
        bankDetailsRepository.save(bankDetailsModel);
        String body = "BankDetails is added succesfully with id " + bankDetailsModel.getId();
        String subject = "BankDetails Added Succesfully";
        mailService.send(bankDetailsModel.getEmailId(), subject, body);
        return bankDetailsModel;
        }
        throw new BankNotFoundException("Invalid Token", 500);
    }
    
    @Override
    public BankDetailsModel updateBank(Long id, BankDetailsDTO bankDetailsDTO, String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {	
        Optional<BankDetailsModel> bankIdPresent = bankDetailsRepository.findById(id);
        if (bankIdPresent.isPresent()) {
            bankIdPresent.get().setAccountNo(bankDetailsDTO.getAccountNo());
            bankIdPresent.get().setIfscCode(bankDetailsDTO.getIfscCode());
            bankIdPresent.get().setBranch(bankDetailsDTO.getBranch());
            bankIdPresent.get().setLinkedMobNo(bankDetailsDTO.getLinkedMobNo());
            bankIdPresent.get().setAccountHolderName(bankDetailsDTO.getAccountHolderName());
            bankIdPresent.get().setEmailId(bankDetailsDTO.getEmailId());
            bankIdPresent.get().setCreatorDateTime(LocalDateTime.now());
            bankIdPresent.get().setUpdatedDateTime(LocalDateTime.now());
            bankDetailsRepository.save(bankIdPresent.get());
            String body = "BankDetails is added succesfully with id " + bankIdPresent.get().getId();
            String subject = "BankDetails Added Succesfully";
            mailService.send(bankIdPresent.get().getEmailId(), subject, body);
            return bankIdPresent.get();
        }
        throw new BankNotFoundException("BankDetails Not Found", 500);
        }
        throw new BankNotFoundException("Invalid Token", 500);
    }

    @Override
    public List<BankDetailsModel> getAllBankDetails(String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            List<BankDetailsModel> isBankPresent = bankDetailsRepository.findAll();
            if (isBankPresent.size() > 0) {
                return isBankPresent;
            }
            throw new BankNotFoundException("BankDetails Not Found", 500);
        }
        throw new BankNotFoundException("Invalid Token", 500);
    }

    @Override
    public BankDetailsModel getBankById(Long id, String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<BankDetailsModel> isBankIdPresent = bankDetailsRepository.findById(id);
            if (isBankIdPresent.isPresent()) {
                return isBankIdPresent.get();
            }
            throw new BankNotFoundException("BankDetails Not Found", 500);
        }
        throw new BankNotFoundException("Invalid Token", 500);
    }

    @Override
    public BankDetailsModel deleteBank(Long id, String token) {
    	boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<BankDetailsModel> isBankIdPresent = bankDetailsRepository.findById(id);
            if (isBankIdPresent.isPresent()) {
                bankDetailsRepository.delete(isBankIdPresent.get());
                String body = "BankDetails is deleted succesfully with id " + isBankIdPresent.get().getId();
                String subject = "BankDetails Deleted Succesfully";
                mailService.send(isBankIdPresent.get().getEmailId(), subject, body);
                return isBankIdPresent.get();
            }
            throw new BankNotFoundException("BankDetails Not Found", 500);
        }
        throw new BankNotFoundException("Invalid Token", 500);
    }

}
