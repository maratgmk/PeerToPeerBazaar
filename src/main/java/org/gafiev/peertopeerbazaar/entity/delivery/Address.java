package org.gafiev.peertopeerbazaar.entity.delivery;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.Order;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "town")
    private String town;
    @Column(name = "street")
    private String street;
    @Column(name = "number_building")
    private int numberBuilding;
    @Column(name = "zip_code")
    private int zipCode;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @MapsId
    private Order order;

//    @OneToOne()
//    private GpsData gpsData; // ??
}

// TODO в сервисе оформления заказа должна быть проверка в виде отдельной интеграции,
//  что товар можно доставить по адресу, по координатам
// запретные зоны
