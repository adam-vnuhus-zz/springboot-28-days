package com.onemount.cinema.model;

import com.onemount.cinema.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String fullName;

    private String hashedPassword;

    private String address;

    private String phone;

    private Date createdAt;

    private Date updatedAt;

    private int point;

    private CustomerType type;

    

    public Customer(String userName, String fullName, String address, String phone, CustomerType type) {
        this.userName = userName;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.type = type;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<OrderLine> orderLines = new ArrayList<>();

    public void addOrderLine(OrderLine orderLine) {
        orderLine.setCustomer(this);
        orderLines.add(orderLine);
    }
}
