package org.gafiev.peertopeerbazaar.service;

import org.gafiev.peertopeerbazaar.entity.delivery.Address;
import org.gafiev.peertopeerbazaar.entity.delivery.Delivery;
import org.gafiev.peertopeerbazaar.entity.delivery.DeliveryStatus;
import org.gafiev.peertopeerbazaar.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional(readOnly = true)
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Transactional
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    /**
     * обновление существующей поставки
     *
     * @param id    идентификатор поставки, которую надо обновить
     * @param deliveryDetails обновленный объект Delivery
     * @return  Delivery объект, или пустое множество, если не найден
     */
//    public Optional<Delivery> updateDelivery(Long id, Delivery delivery) {
//        if (deliveryRepository.existsById(id)) {
//            delivery.setId(id);
//            return Optional.of(deliveryRepository.save(delivery));
//        }
//        return Optional.empty();
//    }

    @Transactional
    public Delivery updateDeliveryDetails(Long id, Delivery deliveryDetails) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id " + id));

        delivery.setDeliveryStatus(deliveryDetails.getDeliveryStatus());
        delivery.setDateTime(deliveryDetails.getDateTime());
        delivery.setOrder(deliveryDetails.getOrder());
        delivery.setAddress(deliveryDetails.getAddress());
        delivery.setQuadcopters(deliveryDetails.getQuadcopters());

        return deliveryRepository.save(delivery);
    }

    @Transactional(readOnly = true)
    public Set<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus) {
        return deliveryRepository.findByDeliveryStatus(deliveryStatus);
    }

    @Transactional(readOnly = true)
    public Set<Delivery> findByAddress(Address address) {
        return deliveryRepository.findByAddress(address);
    }

    @Transactional(readOnly = true)
    public long countByDeliveryStatus(DeliveryStatus deliveryStatus) {
        return deliveryRepository.countByDeliveryStatus(deliveryStatus);
    }

    @Transactional(readOnly = true)
    public Set<Delivery> findDeliveriesWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return deliveryRepository.findDeliveriesWithinDateRange(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public Set<Delivery> findDeliveryByQuadcopterId(Long id){
        return deliveryRepository.findDeliveriesByQuadcopterId(id);
    }

    @Transactional(readOnly = true)
    public Set<Delivery> getDeliveriesByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }

    @Transactional
    public void deleteDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id " + id));
        deliveryRepository.delete(delivery);
    }

}
