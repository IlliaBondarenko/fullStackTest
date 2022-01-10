package com.palitronica.store.data.repository;

import com.palitronica.store.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustomerId(Long customerId);
    @Override
    ArrayList<Customer> findAll();
}
