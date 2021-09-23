package com.zemoso.springboot.ecommercewebsite.dao;

import com.zemoso.springboot.ecommercewebsite.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT u FROM Customer u WHERE u.email = ?1")
    Customer findByEmail(String username);
}
