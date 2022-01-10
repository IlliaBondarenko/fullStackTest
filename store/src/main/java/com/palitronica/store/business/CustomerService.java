package com.palitronica.store.business;

import com.palitronica.store.data.entity.Customer;
import com.palitronica.store.data.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(Customer customer){
        getCustomerRepository().save(customer);
    }

    public ArrayList<Customer> getAllCustomers(){
        return getCustomerRepository().findAll();
    }

    public void deleteCustomer(Customer customer){
        getCustomerRepository().delete(customer);
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
