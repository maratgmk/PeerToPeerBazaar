package org.gafiev.peertopeerbazaar.entity.delivery;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.Order;

import java.util.Set;

/**
 * Сущность Address представляет адрес забора заказа или доставки заказа,
 * в случае отсутствия почтового адреса необходимо пользоваться координатами на местности
 */
@EqualsAndHashCode(exclude = {"orders","deliveries"})
@ToString(exclude = {"orders","deliveries"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    /**
     * поле id есть уникальный идентификатор адреса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * поле town название города
     */
    @Column(name = "town")
    private String town;

    /**
     * поле street имя улицы
     */
    @Column(name = "street")
    private String street;

    /**
     * поле numberBuilding номер дома
     */
    @Column(name = "number_building")
    private int numberBuilding;

    /**
     * поле zipCode это почтовый индекс
     */
    @Column(name = "zip_code")
    private int zipCode;

    /**
     * поле latitude это широта координаты адреса
     */
    @Column(name = "latitude")
    private double latitude;

    /**
     * поле longitude это долгота координаты адреса
     */
    @Column(name = "longtitude")
    private double longitude;

    /**
     * поле attitude это высота координаты адреса
     */
    @Column(name = "attitude")
    private double attitude;

    /**
     * поле accuracy погрешность измерения координат адреса
     */
    @Column(name = "accuracy")
    private float accuracy;

    /**
     * поле orders содержит набор уникальных заказов, имеет связь с адресом,
     * как один адрес ко многим заказам
     */
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    /**
     * поле deliveries содержит набор уникальных доставок, имеет связь с адресом,
     * как один адрес ко многим доставкам
     */
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Delivery> deliveries;

    /**
     * @param order это объект сущности Order
     * метод addOrder добавляет заказ в набор заказов, связанных с этим адресом,
     * а в объект order устанавливает ссылку этот адрес
     */
    public void addOrder(@NonNull Order order){
        this.orders.add(order);
        order.setAddress(this);
    }

    /**
     * метод removeOrder удаляет заказ из набора заказов, связанных с этим адресом,
     * а в сущность order устанавливает нулевую ссылку на этот адрес
     * @param order это объект сущности Order
     */
    public void removeOrder(@NonNull Order order){
        this.orders.remove(order);
        order.setAddress(null);
    }

    /**
     * @param delivery это объект класса Delivery
     * метод addDelivery добавляет доставку во множество доставок, осуществляемых по этому адресу,
     * а в объект order устанавливает этот адрес
     */
    public void addDelivery(@NonNull Delivery delivery){
        this.deliveries.add(delivery);
        delivery.setAddress(this);
    }

    /**
     * @param delivery это объект класса Delivery
     * метод removeDelivery удаляет доставку из множества доставок, отменяет доставку по этому адресу,
     * а в объект delivery устанавливает нулевую ссылку этот адрес
     */
    public void removeDelivery(@NonNull Delivery delivery){
        this.deliveries.remove(delivery);
        delivery.setAddress(null);
    }

}

// TODO в сервисе оформления заказа должна быть проверка в виде отдельной интеграции,
//  что товар можно доставить по адресу, по координатам, что эти координаты не являются запретной зоной

