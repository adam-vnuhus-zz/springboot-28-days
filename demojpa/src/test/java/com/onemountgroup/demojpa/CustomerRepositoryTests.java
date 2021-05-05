package com.onemountgroup.demojpa;

import com.onemountgroup.demojpa.model.Customer;
import com.onemountgroup.demojpa.repository.CustomerRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Sql({ "/customer.sql" })
public class CustomerRepositoryTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@DisplayName("01. Get customer count")
	public void getCustomerCount() {
		assertThat(customerRepository.count()).isGreaterThan(10);
	}

	@Test
	@DisplayName("02. Get customer with id = 5")
	public void get5thCustomer() {
		Optional<Customer> customer = customerRepository.findById(5L);
		if (customer.isPresent()) {
			assertThat(customer.get()).extracting("lastName").isEqualTo("Sparshott");
		}
	}

	@Test
  	@DisplayName("03. Insert a new customer")
  	public void insertNewCustomer() {
		long countBeforeInsert = customerRepository.count();
		Customer newCustomer = new Customer("Neils", "Roskam", "nroskam0@woothemes.com", "6666821422", "Environmental Specialist");
		customerRepository.save(newCustomer);
		long countAfterInsert = customerRepository.count();
		assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
  	}

	@Test
	@DisplayName("04. Get customer with email = kgaine6@boston.com")
	public void testGetCustomerWithEmail(){
		String email = "kgaine6@boston.com";
		List<Customer> customers = customerRepository.findByEmail(email);
    	assertThat(customers.get(0).getEmail()).isEqualTo(email);   
	}

	@Test
	@DisplayName("05. Delete a customer with id = 10L")
	public void testDeleteCustomer(){
		customerRepository.deleteById(10L);
		Optional<Customer> customer = customerRepository.findById(10L);
		assertThat(customer).isEmpty();
	}
}
