package com.zemoso.springboot.ecommercewebsite.service;

import com.zemoso.springboot.ecommercewebsite.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int id);
    public void save(Product product);
    public void deleteById(int id);
}
