package org.gafiev.peertopeerbazaar.entity.delivery;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "address_id")
    private Long addressId;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "drone_id")
    private Long droneId;
}
