package org.gafiev.peertopeerbazaar.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.gafiev.peertopeerbazaar.entity.order.Order;
import org.gafiev.peertopeerbazaar.entity.payment.PaymentAccount;
import org.gafiev.peertopeerbazaar.entity.product.Product;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"sellerOrders","buyerOrders","paymentAccounts"})
@ToString(exclude = {"sellerOrders","buyerOrders","paymentAccounts"})
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    /**
     * ratingSeller from 0 to 100;
     * ratingBuyer  from 0 to 100;
     */
    @Column(name = "rating_seller")
    private Integer ratingSeller;
    @Column(name = "rating_buyer")
    private Integer ratingBuyer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> sellerOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> buyerOrders;

    @OneToMany(mappedBy = "payment_account", cascade = CascadeType.ALL, orphanRemoval = true)
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
