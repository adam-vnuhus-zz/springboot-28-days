package com.onemount.cinema.service;

import javax.transaction.Transactional;

import com.onemount.cinema.enums.CustomerType;
import com.onemount.cinema.enums.OrderLineStatus;
import com.onemount.cinema.model.Customer;
import com.onemount.cinema.model.OrderLine;
import com.onemount.cinema.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void generateCustomer() {
        Customer dung = new Customer("dungnv1", "Nguyễn Vũ Dũng", "99 ngõ 69 Hàng Bài", "0913678999",
                CustomerType.VIP);
        dung.addOrderLine(new OrderLine(OrderLineStatus.SUCCESS));

        Customer nam = new Customer("namnv", "Nguyễn Vũ Nam", "69 ngõ 99 Hàng Bông", "0912678998", 
                CustomerType.NORMAL);
        nam.addOrderLine(new OrderLine(OrderLineStatus.FAILURE));

        customerRepository.save(dung);
        customerRepository.save(nam);
    }
}
