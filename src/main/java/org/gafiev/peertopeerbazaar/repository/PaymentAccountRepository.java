package org.gafiev.peertopeerbazaar.repository;

import org.gafiev.peertopeerbazaar.entity.payment.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PaymentAccountRepository extends JpaRepository<PaymentAccount,Long> {

    Optional<PaymentAccount> findByUserId(Long userId);
}
