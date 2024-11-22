package org.example.entity.user;

import lombok.*;
import org.example.entity.order.Order;
import org.example.entity.payment.PaymentAccount;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"sellerOrders","buyerOrders","paymentAccounts"})
@ToString(exclude = {"sellerOrders","buyerOrders","paymentAccounts"})
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Role role;
    /**
     * ratingSeller from 0 to 100;
     * ratingBuyer
     */
    private int ratingSeller;
    private int ratingBuyer;
    private Set<Order> sellerOrders;
    private Set<Order> buyerOrders;
    private Set<PaymentAccount> paymentAccounts;

    public void addPaymentAccount(@NonNull PaymentAccount paymentAccount){
        this.paymentAccounts.add(paymentAccount);
        paymentAccount.setUser(this);
    }
    public void removePaymentAccount(@NonNull PaymentAccount paymentAccount){
        this.paymentAccounts.remove(paymentAccount);
        paymentAccount.setUser(null);
    }
    //TODO для полей sellerOrders buyerOrders написать аналогичные методы
}
