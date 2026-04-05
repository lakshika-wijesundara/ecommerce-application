package com.prac.ecom_project.service;

import com.prac.ecom_project.model.Order;

import java.util.List;

public interface OrderService
{
    Order createOder(Order order);

    List<Order> getAllOrders();

    Order updateOrder(Long id,Order order);

    Order deleteOrder(Long id);

    List<Order> searchOrders(String keyword);



}
