package org.example.entity.order;

import lombok.*;
import org.example.entity.delivery.Address;
import org.example.entity.delivery.GpsData;
import org.example.entity.user.User;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private Long id;
    private User buyer;
    private User seller;
    private Set<OrderItem> items;//TODO добавить анотации от рекурсии. Добавить методы add remove
    private StatusOrder status;
    private Double totalWeight;
    private Double totalVolume;
    private LocalDateTime creationDateTime;
    private LocalDateTime finishDateTime;
    private Address address;
    private GpsData location;


}
