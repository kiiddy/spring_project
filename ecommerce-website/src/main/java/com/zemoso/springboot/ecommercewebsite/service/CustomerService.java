package com.zemoso.springboot.ecommercewebsite.service;

import com.zemoso.springboot.ecommercewebsite.entity.Customer;

import java.util.List;

public interface CustomerService{
    public List<Customer> findAll();
    public Customer findById(int id);
    public void save(Customer customer);
    public void deleteById(int id);

    Customer findByEmail(String username);
    public boolean customerExists(String email);
}
