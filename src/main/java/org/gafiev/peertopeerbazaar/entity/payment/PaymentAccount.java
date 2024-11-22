package org.gafiev.peertopeerbazaar.entity;

import lombok.*;
import org.example.entity.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentAccount {
    private Long id;
    private User user;
    private String accountNumber;
    private String currency;
    private BigDecimal balance;
    private String owner;
    private ModePay type; // Например, BANK, WALLET
    private String status; // Например, ACTIVE, BLOCKED
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String bankCode; // Или код платежной системы
    private BigDecimal limit; // Лимит на операции
    private Boolean isVerified;
    private String securityToken;

}


//Возможные расширения:
//
//История операций (отдельная сущность, связанная с PaymentAccount).
//Логирование изменения баланса.
//Поддержка нескольких типов верификаций (например, через VerificationStatus).
