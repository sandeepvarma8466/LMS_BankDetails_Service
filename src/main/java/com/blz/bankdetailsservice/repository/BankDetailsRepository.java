package com.blz.bankdetailsservice.repository;

import com.blz.bankdetailsservice.model.BankDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetailsModel, Long> {
}
