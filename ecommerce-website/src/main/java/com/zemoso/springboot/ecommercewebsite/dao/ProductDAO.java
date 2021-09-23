package com.zemoso.springboot.ecommercewebsite.dao;

import com.zemoso.springboot.ecommercewebsite.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    public List<Product> findAll();
    public Optional<Product> findById(int id);
    public void save(Product product);
    public void deleteById(int id);
}
