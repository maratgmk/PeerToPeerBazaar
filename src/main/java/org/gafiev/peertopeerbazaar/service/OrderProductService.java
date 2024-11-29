package org.gafiev.peertopeerbazaar.service;

import org.gafiev.peertopeerbazaar.entity.order.OrderProduct;
import org.gafiev.peertopeerbazaar.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository){
        this.orderProductRepository = orderProductRepository;
    }

    @Transactional
    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Transactional(readOnly = true)
    public Optional<OrderProduct> getOrderProductById(Long productId) {
        return orderProductRepository.findById(productId);
    }

    @Transactional(readOnly = true)
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Set<OrderProduct> getOrderProductsByOrderId(Long orderId) {
        return orderProductRepository.findByOrderId(orderId);
    }

    @Transactional
    public OrderProduct updateOrderProduct(Long productId, OrderProduct orderProductDetails) {
        OrderProduct orderProduct = orderProductRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("OrderProduct not found with id " + productId));

        orderProduct.setPortionCount(orderProductDetails.getPortionCount());
        orderProduct.setComment(orderProductDetails.getComment());
        orderProduct.setOrder(orderProductDetails.getOrder());
        orderProduct.setProduct(orderProductDetails.getProduct());

        return orderProductRepository.save(orderProduct);
    }

    @Transactional
    public void deleteOrderProduct(Long productId) {
        OrderProduct orderProduct = orderProductRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("OrderProduct not found with id " + productId));
        orderProductRepository.delete(orderProduct);
    }

}
