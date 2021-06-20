package com.onemount.onecinema.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.onemount.onecinema.model.Customer;
import com.onemount.onecinema.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  @PersistenceContext private EntityManager em;
  @Autowired private CustomerRepository customerRepo;
  
  public Customer insertOrUpdate(String mobile, String email) {
    Optional<Customer> oCustomer = customerRepo.findByMobile(mobile);
    Customer customer;
    if (oCustomer.isPresent()) {
      customer = oCustomer.get();
      if (!customer.getEmail().equals(email)) {
        customer.setEmail(email);
        em.persist(customer);
      }
    } else {
      customer = Customer.builder()
      .mobile(mobile)
      .email(email)
      .build();
      em.persist(customer);      
    }
    return customer;
  }
}
