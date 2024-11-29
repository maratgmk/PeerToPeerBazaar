package org.gafiev.peertopeerbazaar.service;

import org.gafiev.peertopeerbazaar.entity.order.Order;
import org.gafiev.peertopeerbazaar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Set<Order> findByTotalWeight(Double totalWeight){
        return orderRepository.findByTotalWeight(totalWeight);
    }

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Set<Order> getOrdersByBuyerId(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    @Transactional(readOnly = true)
    public Set<Order> getOrdersBySellerId(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Transactional
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));

        order.setOrderStatus(orderDetails.getOrderStatus());
        order.setTotalWeight(orderDetails.getTotalWeight());
        order.setTotalVolume(orderDetails.getTotalVolume());
        order.setCreationDateTime(orderDetails.getCreationDateTime());
        order.setFinishDateTime(orderDetails.getFinishDateTime());
        order.setAddress(orderDetails.getAddress());
        order.setBuyer(orderDetails.getBuyer());
        order.setSeller(orderDetails.getSeller());

        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
        orderRepository.delete(order);
    }

}
