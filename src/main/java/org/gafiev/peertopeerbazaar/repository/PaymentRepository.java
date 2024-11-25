package org.gafiev.peertopeerbazaar.repository;

import org.gafiev.peertopeerbazaar.entity.payment.Payment;
import org.gafiev.peertopeerbazaar.entity.payment.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
  //  Optional<Payment> findByIdWithOrder(Long id);
    Set<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);
    Optional<Payment> findByOrder_Id(Long orderId);



}
