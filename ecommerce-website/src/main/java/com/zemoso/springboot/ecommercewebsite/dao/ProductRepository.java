package com.zemoso.springboot.ecommercewebsite.dao;

import com.zemoso.springboot.ecommercewebsite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
