package com.onemount.onecinema.repository;

import java.util.Optional;

import com.onemount.onecinema.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
  Optional<Customer> findByMobile(String mobile);
}
