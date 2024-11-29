package org.gafiev.peertopeerbazaar.entity.order;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.product.Product;

/**
 * сущность OrderProduct описывает продукт в заказе
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_product")
public class OrderProduct {
    /**
     * поле productId  идентификатор продукта в заказе,
     * он же является уникальным идентификатором в сущности Product
     */
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    /**
     * поле portionCount указывает на количество порций продукта в заказе
     */
    @Column(name = "portion_count")
    private Integer portionCount;

    /**
     * поле comment описывает характеристики продукта в заказе
     */
    @Column(name = "comment")
    private String comment;

    /**
     * поле order есть обратная сторона в отношении множества продуктов в заказе к этому заказу,
     * заказ передаёт свой идентификатор в качестве внешнего ключа к таблице order_product,
     * множество продуктов в заказе есть владеющая сторона в отношении ManyToOne,
     * владеет внешним ключом от заказа, FetchType.LAZY означает, что при вызове из БД
     * продукта в заказе сам этот заказ вызываться не будет,
     * аннотация cascade = {CascadeType.MERGE, CascadeType.PERSIST} означает,
     * что при обновлении продукта, будет слияние с другим существующим заказом,
     * или с новым заказом, который будет сохранён в БД
     */
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Order order;

    /**
     * поле product является обратной стороной в отношении Product To OrderProduct,
     * по ключу идентификатора продукта образуется внешняя связь с прдуктом в заказе,
     * атрибут FetchType.LAZY относится к объекту сущности Product и означает, что он не будет
     * загружаться из БД при загрузке объекта сущности OrderProduct, а только при явном вызове,
     * атрибут orphanRemoval = true относится к полю product и означает, что если в объекте OrderProduct
     * будет установлена ссылка "null" на product, то этот объект удалится также из БД
     */
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @MapsId
    private Product product;
}
