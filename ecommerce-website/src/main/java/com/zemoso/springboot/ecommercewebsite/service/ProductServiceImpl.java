package com.zemoso.springboot.ecommercewebsite.service;

import com.zemoso.springboot.ecommercewebsite.dao.ProductDAO;
import com.zemoso.springboot.ecommercewebsite.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        Optional<Product> result = productDAO.findById(id);
        Product product = null;
        if (result.isPresent()){
            product = result.get();
        }
        else{
            throw new RuntimeException("product not found with id - "+id);
        }
        return product;
    }

    @Override
    public void save(Product product) {
        productDAO.save(product);
    }

    @Override
    public void deleteById(int id) {
        productDAO.deleteById(id);
    }
}
