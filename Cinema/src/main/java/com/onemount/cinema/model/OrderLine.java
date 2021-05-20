package com.onemount.cinema.model;

import com.onemount.cinema.enums.OrderLineStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "order_line")
@Table(name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date createdAt;

    private Date updatedAt;

    // private Event event;
    //
    // private Order order;
    //
    // private Customer customer;

    private OrderLineStatus status;

    public OrderLine(OrderLineStatus status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
