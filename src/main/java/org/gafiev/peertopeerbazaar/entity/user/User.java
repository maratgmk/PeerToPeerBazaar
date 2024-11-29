package org.gafiev.peertopeerbazaar.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.Order;
import org.gafiev.peertopeerbazaar.entity.payment.PaymentAccount;
import org.gafiev.peertopeerbazaar.entity.product.Product;

import java.util.Set;

/**
 * сущность User является пользователем приложения,
 * пользователь может быть продавцом(он же создатель продукта), покупателем
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"sellerOrders","buyerOrders","paymentAccounts","products"})
@ToString(exclude = {"sellerOrders","buyerOrders","paymentAccounts","products"})
@Entity
@Table(name="users")
public class User {
    /**
     * поле id является уникальным идентификатором пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * поле firstName есть имя пользователя
     */
    @Column(name="first_name")
    private String firstName;

    /**
     * поле lastName есть фамилия пользователя
     */
    @Column(name="last_name")
    private String lastName;

    /**
     * поле email электронная почта пользователя
     */
    @Column(name = "email",unique = true)
    private String email;

    /**
     * поле password есть пароль пользователя
     */
    @Column(name = "password")
    private String password;

    /**
     * поле phone номер телефона пользователя
     */
    @Column(name = "phone")
    private String phone;

    /**
     * поле role отражает под каким видом  пользователь ввошёл в приложение
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * поле ratingSeller показывает рейтинг продавца от 0 до 100;
     * ratingBuyer  from 0 to 100;
     */
    @Column(name = "rating_seller")
    private Integer ratingSeller;

    /**
     * поле ratingBuyer показывает рейтинг покупателя от 0 до 100;
     */
    @Column(name = "rating_buyer")
    private Integer ratingBuyer;

    /**
     * поле products есть множество продуктов созданных пользователем (продавцом)
     * множество заказов сюръективно отображается в образ продавца,
     * и является владеющей стороной, владеет ключём продавца,
     * аннотация cascade = CascadeType.ALL означает, что все изменения на обратной стороне,
     * т.е. у продавца, каскадом произойдут и на владеющей стороне,т.е. во множестве заказов,
     * аннотация orphanRemoval = true означает, что при удалении продавца на обратной стороне,
     * из БД автоматически удалится владеющая сторона, т.е. заказы данного продавца
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    /**
     * поле sellerOrders является множеством звказов продавца,
     * множество заказов сюръективно отображается в образ продавца,
     * и является владеющей стороной, владеет ключём продавца,
     * аннотация cascade = CascadeType.ALL означает, что все изменения на обратной стороне,
     * т.е. у продавца, каскадом произойдут и на владеющей стороне,т.е. во множестве заказов,
     * аннотация orphanRemoval = true означает, что при удалении продавца на обратной стороне,
     * из БД автоматически удалится владеющая сторона, т.е. заказы данного продавца
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> sellerOrders;

    /**
     * поле buyerOrders является множеством звказов покупателя
     * множество заказов сюръективно отображается в образ покупателя,
     * и является владеющей стороной, владеет ключём покупателя,
     * атрибут cascade = CascadeType.ALL означает, что все изменения на обратной стороне,
     * т.е. у покупателя, каскадом произойдут и на владеющей стороне,т.е. во множестве заказов,
     * атрибут orphanRemoval = true означает, что при удалении покупателя на обратной стороне,
     * из БД автоматически удалится владеющая сторона, т.е. заказы данного покупателя
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> buyerOrders;

    /**
     * поле paymentAccounts является множеством платежных счетов пользователя
     * и является владеющей стороной, владеет ключём пользователя,
     * атрибут mappedBy = "user" означает, что в классе PaymentAccount есть поле "user",
     * атрибут CascadeType.ALL относится к paymentAccounts и означает, что
     * при всех изменениях в сущности пользователя, каскадом изменятся пользователи в
     * таблице "payment_account", атрибут orphanRemoval = true означает,
     * что при удалении пользователя на обратной стороне,
     * из БД автоматически удалится владеющая сторона, т.е. платежные аккаунты данного пользователя
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PaymentAccount> paymentAccounts;

    public void addProduct(@NonNull Product product){
        this.products.add(product);
        product.setAuthor(this);
    }
    public void removeProduct(@NonNull Product product){
        this.products.remove(product);
        product.setAuthor(null);
    }
    public void addPaymentAccount(@NonNull PaymentAccount paymentAccount){
        this.paymentAccounts.add(paymentAccount);
        paymentAccount.setUser(this);
    }
    public void removePaymentAccount(@NonNull PaymentAccount paymentAccount){
        this.paymentAccounts.remove(paymentAccount);
        paymentAccount.setUser(null);
    }
    public void addSellerOrder(@NonNull Order order){
        sellerOrders.add(order);
        order.setSeller(this);
    }
    public void removeSellerOrder(@NonNull Order order){
        sellerOrders.remove(order);
        order.setSeller(null);
    }
    public void addBuyerOrder(@NonNull Order order){
        buyerOrders.add(order);
        order.setBuyer(this);
    }
    public void removeBuyerOrder(@NonNull Order order){
        buyerOrders.remove(order);
        order.setBuyer(null);
    }

}
