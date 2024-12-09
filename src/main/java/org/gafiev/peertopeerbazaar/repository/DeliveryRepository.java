package org.gafiev.peertopeerbazaar.repository;

import org.gafiev.peertopeerbazaar.entity.delivery.Address;
import org.gafiev.peertopeerbazaar.entity.delivery.Delivery;
import org.gafiev.peertopeerbazaar.entity.delivery.DeliveryStatus;
import org.gafiev.peertopeerbazaar.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    @Override
    Optional<Delivery> findById(Long id);

    Set<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus);

    Set<Delivery> findByAddress(Address address);

    Set<Delivery> findByOrder(Order order);



    @Query("SELECT d FROM Delivery d WHERE d.dateTime BETWEEN :startDate AND :endDate")
    Set<Delivery> findDeliveriesWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT d FROM Delivery d JOIN d.quadcopters q WHERE q.id = :quadcopterId")
    Set<Delivery> findDeliveriesByQuadcopterId(Long quadcopterId);

    long countByDeliveryStatus(DeliveryStatus deliveryStatus);

    void deleteByAddress(Address address);

    Set<Delivery> findByOrderId(Long orderId);

}

