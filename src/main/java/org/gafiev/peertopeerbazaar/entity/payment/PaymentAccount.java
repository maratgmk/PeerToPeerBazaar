package org.gafiev.peertopeerbazaar.entity.payment;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "payment_account")
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "currency")
    private String currency;
    @Column(name = "balance")
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private ModePay mode_type;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "bank_code")
    private String bankCode; // Или код платежной системы
    @Column(name = "limit")
    private BigDecimal limit; // Лимит на операции
    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name = "security_token")
    private String securityToken;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User user;

}


//Возможные расширения:
//
//История операций (отдельная сущность, связанная с PaymentAccount).
//Логирование изменения баланса.
//Поддержка нескольких типов верификаций (например, через VerificationStatus).
