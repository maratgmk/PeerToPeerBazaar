package org.gafiev.peertopeerbazaar.entity.product;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.OrderProduct;
import org.gafiev.peertopeerbazaar.entity.user.User;

import java.math.BigDecimal;
import java.util.Set;

/**
 * сущность Product есть продукт, который создаётся продавцом
 * для реализации с помощью данного приложения
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class Product {
    /**
     * поле id есть уникальный идентификатор продукта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * поле name есть название продукта
     */
    @Column(name = "name")
    private String name;

    /**
     * поле description есть описание продукта
     */
    @Column(name = "description")
    private String description;

    /**
     * поле category сообщает какова обобщенная характеристика продукта
     */
    @Enumerated(EnumType.STRING)
    private Category category;

    /**
     * поле portionUnit сообщает в чём измеряется одна порция продукта
     */
    @Enumerated(EnumType.STRING)
    private PortionUnit portionUnit;

    /**
     * поле portionUnitCount есть количество порций продукта
     */
    @Column(name = "portion_unit_count")
    private Integer portionUnitCount;

    /**
     * поле weight сообщает общий вес продукта
     */
    @Column(name = "weight")
    private Double weight;

    /**
     * поле volume сообщает общий объём продукта
     */
    @Column(name = "volume")
    private Double volume;

    /**
     * поле price есть цена за порцию продукта
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * поле imageURI изображение продукта
     */
    @Column(name = "image_uri") //TODO сделать интеграцию с базой фото
    private String imageURI;

    /**
     * поле qrCode является ссылкой на страницу продукта
     */
    @Column(name = "qr_code")
    private String qrCode; //TODO сделать интеграцию с базой QR

    /**
     * поле author указывает на создателя продукта, и является обратной стороной
     * в отношении ManyToOne, передавая свой ключ владеющей стороне (множеству продуктов),
     * аннотация FetchType.LAZY означает, что при загрузке множества продуктов
     * автор (продавец) загружаться не будет, аннотация cascade = {CascadeType.MERGE, CascadeType.PERSIST} означает,
     * что при создании или обновлении продукта, будет заменяться продавец, если он существует,
     * или создаться новый объект продавца
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User author;

    /**
     * поле orderProduct есть владеющая сторона отношения продукта к продукту в заказе,
     * продукт в заказе хранит внешний ключ, который является идентификатором продукта,
     * атрибут mappedBy = "product" означает, сущность OrderProduct имеет поле "product",
     * атрибут cascade = CascadeType.ALL означает, что все операции persist, merge, remove, refresh, detach,
     * произведённые в сущности Product перейдут каскадом (ниспадут) в сущность OrderProduct
     *
     */
    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    private OrderProduct orderProduct;


}
