package com.prac.ecom_project.service.impl;

import com.prac.ecom_project.exceptions.ResourceNotFoundException;
import com.prac.ecom_project.model.Order;

import com.prac.ecom_project.repo.OrderRepo;
import com.prac.ecom_project.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    // Logger - to track what's happening in your app
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final OrderRepo orderRepository;

    public OrderServiceImpl(OrderRepo orderRepository){
        this.orderRepository=orderRepository;
    }

    //CREATE
@Override
    public Order createOrder(Order order){

    if(order==null){
        throw new IllegalArgumentException("Order cannot be null");
    }
             Order saveOrder=  orderRepository.save(order);
            return saveOrder;

    }
@Override
    public List<Order> getAllOrders(){

        List<Order> orders= orderRepository.findAll();
        if (orders.isEmpty()){
            throw new ResourceNotFoundException("No orders found");
        }
        return orders;


    }
  @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
    @Override
    public Order updateOrder(Long id, Order updatedOrder) {
        Order existing = getOrder(id);
        existing.setStatus(updatedOrder.getStatus());
        existing.setTotalAmount(updatedOrder.getTotalAmount());
        return orderRepository.save(existing);}

    public Order deleteOrder(Long id) {
        log.info("Deleting order with id: {}", id);

        Order order = getOrder(id);
        orderRepository.delete(order);
        log.info("Order deleted successfully with id: {}", id);
return order;
    }



    @Override
    public List<Order> searchOrders(String keyword) {
        return orderRepository.findByStatus(keyword);
    }
}
