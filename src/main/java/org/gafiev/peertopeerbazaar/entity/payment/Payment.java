package org.gafiev.peertopeerbazaar.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {
    private Long id;
    private Long orderID;
    private Double amount;
    private ModePay modePay;
    private StatusPayment statusPayment;
    private LocalDateTime dateTime;
}
