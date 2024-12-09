package org.gafiev.peertopeerbazaar.entity.payment;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * сущность PaymentAccount представляет платежный счёт (аккааунт) пользователя
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "payment_account")
public class PaymentAccount {
    /**
     * поле id есть уникальный идентификатор платёжного счёта пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * поле accountNumber есть номер счёта
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * поле currency название предмета платежа
     */
    @Column(name = "currency")
    private String currency;

    /**
     * поле balance состояние счёта
     */
    @Column(name = "balance")
    private BigDecimal balance;

    /**
     * поле mode_type название метода платежа
     */
    @Enumerated(EnumType.STRING)
    private ModePay mode_type;

    /**
     * поле paymentStatus отражает состояние платежа
     */
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    /**
     * поле createdDate дата создания аккаунта платежа ????
     */
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * поле updatedDate дата изменения аккаунта платежа ????
     */
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    /**
     * поле bankCode код банка или платежной системы
     */
    @Column(name = "bank_code")
    private String bankCode; // Или код платежной системы

    /**
     * поле limit лимит на операции
     */
    @Column(name = "limit")
    private BigDecimal limit;

    /**
     * поле isVerified подтверждение кем и чего ????
     */
    @Column(name = "is_verified")
    private Boolean isVerified;

    /**
     * поле securityToken большой секрет  ????
     */
    @Column(name = "security_token")
    private String securityToken;

    /**
     * поле user есть обратная сторона отношений ManyToOne и передаёт свой ключ
     * владеющей стороне (множеству платёжных аккаунтов пользователя),
     * атрибут FetchType.LAZY относится к сущности User и означает, что без явного вызова
     * не будет загружаться из БД при вызове сущности PaymentAccount (множества сущностей ?),
     * атрибут {CascadeType.MERGE,CascadeType.PERSIST} относится к объекту User и означает,
     * что при создании или обновлении платежного аккаунта, будет заменяться пользователь, если он существует,
     * или создаться новый объект пользователя
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User user;

}


//Возможные расширения:
//
//История операций (отдельная сущность, связанная с PaymentAccount).
//Логирование изменения баланса.
//Поддержка нескольких типов верификаций (например, через VerificationStatus).
