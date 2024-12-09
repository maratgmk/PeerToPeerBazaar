package org.gafiev.peertopeerbazaar.entity.delivery;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * сущность Quadcopter осуществляет обмен заказов между пользователями
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quadcopter")
public class Quadcopter {
   /**
    * поле id есть уникальный идентификатор квадрокоптера
    */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   /**
    * поле latitude это широта положения квадрокоптера
    */
   @Column(name = "latitude")
   private double latitude;
   /**
    * поле longitude это долгота положения квадрокоптера
    */
   @Column(name = "longtitude")
   private double longitude;

   /**
    * поле attitude это высота положения квадрокоптера
    */
   @Column(name = "attitude")
   private double attitude;

   /**
    * поле accuracy погрешность показателя измерения положения
    */
   @Column(name = "accuracy")
   private float accuracy;

   /**
    * поле timeStamp показывает время снятия показаний измерения
    */
   @Column(name = "time_stamp")
   private long timeStamp;

   /**
    * поле speed показатель скорости квадрокоптера
    */
   @Column(name = "speed")
   private float speed;

   /**
    * поле bearing показывает угол отклонения квадрокоптера от заданного направления
    */
   @Column(name = "bearing")
   private float bearing;

   /**
    * поле deliveries есть набор уникальных доставок,
    * связь множества квадрокоптеров со множеством доставок,
    * в БД создаётся новая таблица JoinTable "quadcopter_delivery",
    * со внешними ключами quadcopter_id и delivery_id
    */
   @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
   @JoinTable(
           name = "quadcopter_delivery",
           joinColumns = @JoinColumn(name = "quadcopter_id"),
           inverseJoinColumns = @JoinColumn(name = "delivery_id")
   )
   private Set<Delivery> deliveries;

}
