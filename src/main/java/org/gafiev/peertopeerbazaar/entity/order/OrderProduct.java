package org.gafiev.peertopeerbazaar.entity.order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.product.Product;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItem {
    private Product product;
    private Integer portionCount;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Order order;
}
