package org.gafiev.peertopeerbazaar.service;

import org.gafiev.peertopeerbazaar.entity.payment.Payment;
import org.gafiev.peertopeerbazaar.entity.payment.PaymentStatus;
import org.gafiev.peertopeerbazaar.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

    @Service
    public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional(readOnly = true)
    public Set<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus) {
        return paymentRepository.findAllByPaymentStatus(paymentStatus);
    }

    @Transactional(readOnly = true)
    public Optional<Payment> findByOrder_Id(Long orderId) {
        return paymentRepository.findByOrder_Id(orderId);
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Transactional(readOnly = true)
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Payment> getAllOrders() {
        return paymentRepository.findAll();
    }

    @Transactional
    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));

        payment.setPaymentStatus(paymentDetails.getPaymentStatus());
        payment.setModePay(paymentDetails.getModePay());
        payment.setAmount(paymentDetails.getAmount());
        payment.setDateTime(paymentDetails.getDateTime());
        return paymentRepository.save(payment);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
        paymentRepository.delete(payment);
    }
}
