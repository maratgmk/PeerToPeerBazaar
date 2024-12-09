package org.gafiev.peertopeerbazaar.entity.order;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.delivery.Address;
import org.gafiev.peertopeerbazaar.entity.payment.Payment;
import org.gafiev.peertopeerbazaar.entity.user.User;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * сущность Order есть заказ, созданный продавцом, и приобретенный покупателем
 */
@EqualsAndHashCode(exclude = "products")
@ToString(exclude = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    /**
     * поле id уникальный идентификатор заказа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * поле orderStatus показывает состояние заказа
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /**
     * поле totalWeight отображает общий вес заказа
     */
    @Column(name = "total_weight")
    private Double totalWeight;

    /**
     * поле totalVolume отображает общий объём заказа
     */
    @Column(name = "total_volume")
    private Double totalVolume;

    /**
     * поле creationDateTime сообщает о дате и времени создания заказа
     */
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    /**
     * поле finishDateTime сообщает о дате и времени доставки заказа
     */
    @Column(name = "finish_date_time")
    private LocalDateTime finishDateTime;

    /**
     * поле payment есть владеющая сторона связи с заказом,
     * то есть образ при биекции,
     * и содержит внешний ключ, совпадающий с идентификатором заказа,
     * причём загрузка объекта payment совершается в стиле Eager,
     * то есть вместе с объектом заказа,
     * cascade = CascadeType.ALL означает, что все изменения в заказе,
     * каскадом изменятся в платеже,
     * orphanRemoval = true означает что, при удалении заказа,
     * автоматически удалится платёж из БД
     */
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    /**
     * поле address есть объект сущности Address, имеет связь с множеством заказов,
     * множество заказов связано с одним адресом,
     * address является обратной стороной отношений, является образом сюръекции,
     * передаёт свой индентификатор в качестве внешнего ключа к множеству заказов,
     * причём загрузка объекта address отсутствует при вызове загрузки объекта заказа
     * аннотация cascade = {CascadeType.MERGE, CascadeType.PERSIST} означает,
     * что при обновлении заказа, будет слияние с другим существующим адресом,
     * или с новым адресом, который сохранится в БД
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Address address;

    /**
     * поле buyer есть объект сущности User, имеет связь с множеством заказов,
     * множество заказов связано с одним адресом,
     * buyer является обратной стороной отношений, является образом сюръекции,
     * передаёт свой индентификатор в качестве внешнего ключа к множеству заказов,
     * причём загрузка объекта buyer отсутствует при вызове загрузки объекта заказа,
     * аннотация cascade = {CascadeType.MERGE, CascadeType.PERSIST} означает,
     * что при обновлении заказа, будет слияние с другим существующим продавцом,
     * или с новым продавцом, который сохранится в БД
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User buyer;

    /**
     * поле seller есть объект сущности User, имеет связь с множеством заказов,
     * множество заказов связано с одним покупателем,
     * seller является обратной стороной отношений, является образом сюръекции,
     * передаёт свой индентификатор в качестве внешнего ключа к множеству заказов,
     * причём загрузка объекта seller отсутствует при вызове загрузки объекта заказа,
     * аннотация cascade = {CascadeType.MERGE, CascadeType.PERSIST} означает,
     * что при обновлении заказа, будет слияние с другим существующим покупателем,
     * или с новым покупателем, который будет сохранён в БД
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User seller;

    /**
     * поле products есть множество продуктов принадлежащих одному заказу,
     * поле есть владеющей стороной, владеет идентификаторм заказа в качестве внешнего ключа,
     * mappedBy = "order" означает, что множество продуктов проецируются на этот заказ,
     * сюрьективное отображение, заказ есть обратная сторона, есть образ при этом отображении,
     * аннотация cascade = CascadeType.ALL означает, что все изменения на обратной стороне,
     * т.е. у заказа, каскадом произойдут и на владеющей стороне,т.е. во множестве продуктов,
     * аннотация orphanRemoval = true означает, что при удалении заказа на обратной стороне,
     * из БД автоматически удалится владеющая сторона, т.е. продукты данного заказа
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProduct> products;

    /**
     * @param orderProduct есть продукт в заказе,
     * метод addOrderProduct добавляет продукт во множество продуктов в заказе
     */
    public void addOrderProduct(@NonNull OrderProduct orderProduct){
        products.add(orderProduct);
        orderProduct.setOrder(this);
    }

    /**
     * @param orderProduct есть продукт в заказе,
     * метод removeOrderProduct удаляет продукт из множества продуктов в заказе.
     * orderProduct.setOrder(null) означает удаление ордера,
     * что вызовет автоматически orphanRemoval = true
     */
    public void removeOrderProduct(@NonNull OrderProduct orderProduct){
        products.remove(orderProduct);
        orderProduct.setOrder(null);
    }
}
