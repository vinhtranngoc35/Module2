package com.hotel.service;

import com.hotel.model.Customer;
import com.hotel.repository.CustomerRepository;

import java.util.List;

public interface CustomerService {
    Iterable<Customer> findAll();

    Customer findById(Long id);

    void save(Customer book);

    void remove(Long id);
    List<Customer> findAllByQuery();
}
