package org.gafiev.peertopeerbazaar.entity.delivery;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.Order;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * сущность Delivery отражает факт доставки заказа по указанному адресу конкретным квадрокоптером
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "delivery")
public class Delivery {
    /**
     * поле id есть уникальный номер доставки заказа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * поле deliveryStatus показывает сотояние доставки заказа
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    /**
     * поле dateTime есть показывает время доставки заказа
     */
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    /**
     * поле order есть сущность заказа, который доставляется от продавца к покупателю,
     * связь - одна доставка к одному заказу
     */
    @OneToOne()
    private Order order;

    /**
     * поле address есть сущность адрес по которому осуществляется доставка,
     * связь - много доставок по одному адресу
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Address address;

    /**
     * поле quadcopters есть уникальное множество квадрокоптеров, совершившие доставки заказа,
     * связь ManyToMany множество доставок со множеством квадрокоптеров,
     * атрибут mappedBy = "deliveries" означает, что в классе Quadcopter есть поле "deliveries",
     *
     */
    @ManyToMany(mappedBy = "deliveries",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY )
    private Set<Quadcopter> quadcopters;
}
