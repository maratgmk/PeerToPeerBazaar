package org.gafiev.peertopeerbazaar.entity.payment;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private ModePay modePay;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @OneToOne(fetch = FetchType.LAZY,orphanRemoval = true)
    @MapsId
    private Order order;
}
