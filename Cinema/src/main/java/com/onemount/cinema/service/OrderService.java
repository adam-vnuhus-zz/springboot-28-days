package com.onemount.cinema.service;

import javax.transaction.Transactional;

import com.onemount.cinema.enums.CustomerType;
import com.onemount.cinema.enums.OrderLineStatus;
import com.onemount.cinema.model.Customer;
import com.onemount.cinema.model.Order;
import com.onemount.cinema.model.OrderLine;
import com.onemount.cinema.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void generateOrder() {
        Order order1 = new Order(135000,0.3f);
        order1.addOrderLine(new OrderLine(OrderLineStatus.SUCCESS));

        Customer nam = new Customer("namnv", "Nguyễn Vũ Nam", "69 ngõ 99 Hàng Bông", "0912678998", 
                CustomerType.NORMAL);
        nam.addOrderLine(new OrderLine(OrderLineStatus.FAILURE));

        // customerRepository.save(dung);
        // customerRepository.save(nam);
    }
}
