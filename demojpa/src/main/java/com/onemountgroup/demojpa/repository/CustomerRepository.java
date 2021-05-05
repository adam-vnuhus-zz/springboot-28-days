package com.onemountgroup.demojpa.repository;

import java.util.List;

import com.onemountgroup.demojpa.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByEmail(String email);
}
