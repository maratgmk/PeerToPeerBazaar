package org.gafiev.peertopeerbazaar.service;


import org.gafiev.peertopeerbazaar.entity.payment.PaymentAccount;
import org.gafiev.peertopeerbazaar.repository.PaymentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentAccountService {
    private final PaymentAccountRepository paymentAccountRepository;

    @Autowired
    public PaymentAccountService(PaymentAccountRepository paymentAccountRepository){
        this.paymentAccountRepository = paymentAccountRepository;
    }

    @Transactional(readOnly = true)
    Optional<PaymentAccount> findByUserId(Long userId){
        return paymentAccountRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    List<PaymentAccount> findAll(){
        return paymentAccountRepository.findAll();
    }

    @Transactional
    public PaymentAccount createPaymentAccount(PaymentAccount paymentAccount) {
        return paymentAccountRepository.save(paymentAccount);
    }

    @Transactional
    public PaymentAccount updatePaymentAccount(Long id, PaymentAccount paymentAccountDetails) {
        PaymentAccount paymentAccount = paymentAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentAccount not found with id " + id));

        paymentAccount.setAccountNumber(paymentAccountDetails.getAccountNumber());
        paymentAccount.setCurrency(paymentAccountDetails.getCurrency());
        paymentAccount.setBalance(paymentAccountDetails.getBalance());
        paymentAccount.setMode_type(paymentAccountDetails.getMode_type());
        paymentAccount.setPaymentStatus(paymentAccountDetails.getPaymentStatus());
        paymentAccount.setCreatedDate(paymentAccountDetails.getCreatedDate());
        paymentAccount.setUpdatedDate(paymentAccountDetails.getUpdatedDate());
        paymentAccount.setBankCode(paymentAccountDetails.getBankCode());
        paymentAccount.setLimit(paymentAccountDetails.getLimit());
        paymentAccount.setIsVerified(paymentAccountDetails.getIsVerified());
        paymentAccount.setSecurityToken(paymentAccountDetails.getSecurityToken());
        paymentAccount.setUser (paymentAccountDetails.getUser ());

        return paymentAccountRepository.save(paymentAccount);
    }

    @Transactional
    public void deletePaymentAccount(Long id) {
        PaymentAccount paymentAccount = paymentAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentAccount not found with id " + id));
        paymentAccountRepository.delete(paymentAccount);
    }

}
