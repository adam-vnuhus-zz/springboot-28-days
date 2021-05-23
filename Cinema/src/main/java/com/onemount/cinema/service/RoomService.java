package com.onemount.cinema.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.onemount.cinema.repository.CinemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    
    @Autowired
    private CinemaRepository cinemaRepository;

    @Transactional
    public void generateOrder() {
        var cinema1 = cinemaRepository.findByName("CGV Vincom Center Bà Triệu");
        
        Order order1 = new Order(135000, 0.3f);
        order1.addOrderLine(new OrderLine(OrderLineStatus.SUCCESS));

        Customer nam = new Customer("namnv", "Nguyễn Vũ Nam", "69 ngõ 99 Hàng Bông", "0912678998", CustomerType.NORMAL);
        nam.addOrderLine(new OrderLine(OrderLineStatus.FAILURE));

        // customerRepository.save(dung);
        // customerRepository.save(nam);
    }
}
