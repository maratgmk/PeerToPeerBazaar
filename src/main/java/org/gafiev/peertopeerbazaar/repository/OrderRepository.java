package org.gafiev.peertopeerbazaar.repository;

import org.gafiev.peertopeerbazaar.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Set<Order> findByTotalWeight(Double totalWeight);
    Optional<Order> findOrderBy(Long id);
    Set<Order> findByBuyerId(Long buyerId);
    Set<Order> findBySellerId(Long sellerId);
}
