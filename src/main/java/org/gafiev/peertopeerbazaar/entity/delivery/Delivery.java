package org.example.entity.delivery;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Delivery {
    private Long id;
    private Long orderId;
    private StatusDelivery statusDelivery;
    private LocalDateTime dateTime;
    private Long droneId;
}
