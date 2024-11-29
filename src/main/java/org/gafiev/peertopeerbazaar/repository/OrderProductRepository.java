package org.gafiev.peertopeerbazaar.repository;

import org.gafiev.peertopeerbazaar.entity.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {

    Set<OrderProduct> findByOrderId(Long orderId);

}
