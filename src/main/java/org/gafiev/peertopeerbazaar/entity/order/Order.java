package org.gafiev.peertopeerbazaar.entity.order;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.delivery.Address;
import org.gafiev.peertopeerbazaar.entity.delivery.GpsData;
import org.gafiev.peertopeerbazaar.entity.payment.Payment;
import org.gafiev.peertopeerbazaar.entity.user.User;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(exclude = "products")
@ToString(exclude = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "total_weight")
    private Double totalWeight;
    @Column(name = "total_volume")
    private Double totalVolume;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
    @Column(name = "finish_date_time")
    private LocalDateTime finishDateTime;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User seller;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProduct> products;

    public void addOrderProduct(@NonNull OrderProduct orderProduct){
        products.add(orderProduct);
        orderProduct.setOrder(this);
    }
    public void removeOrderItem(@NonNull OrderProduct orderProduct){
        products.remove(orderProduct);
        orderProduct.setOrder(null);
    }
}
