package com.zemoso.springboot.ecommercewebsite.service;

import com.zemoso.springboot.ecommercewebsite.dao.CustomerRepository;
import com.zemoso.springboot.ecommercewebsite.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if (result.isPresent()){
            customer = result.get();
        }
        else{
            throw new RuntimeException("customer not found with id - "+id);
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findByEmail(String username) {
        return customerRepository.findByEmail(username);
    }

   public boolean customerExists(String email){
        if (findByEmail(email) != null){
            return true;
        }
        return false;
   }


}
