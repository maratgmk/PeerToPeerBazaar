package org.gafiev.peertopeerbazaar.entity.payment;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * сущность Payment описывает процесс оплаты заказа
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "payment")
public class Payment {
    /**
     * поле orderId есть идентификатор платежа, одновременно являясь идентификатором заказа,
     * принцип - нет заказа, нет оплаты, и наоборот
     */
    @Id
    @Column(name = "order_id")
    private Long orderId;

    /**
     * поле amount соответствует полная сумма платежа за заказ
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * поле modePay говорит о способе оплаты
     */
    @Enumerated(EnumType.STRING)
    private ModePay modePay;

    /**
     * поле paymentStatus показывает состояние оплаты
     */
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    /**
     * поле dateTime отражает дату и время подтверждения оплаты
     */
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    /**
     * поле order это обратная сторона связи, связывает платёж с заказом,
     * один платеж - один заказ, идентификатор у обоих сущностей одинаков и генерируется на стороне заказа,
     * платеж есть владеющая сторона, владеет идентификатором заказа в качестве внешнего ключа,
     * что отражено в аннотации @MapsId,
     * аннотация FetchType.LAZY означает,что при загрузке объекта payment,
     * объект order загружаться не будет
     */
    @OneToOne(fetch = FetchType.LAZY,orphanRemoval = true)
    @MapsId
    private Order order;
}
