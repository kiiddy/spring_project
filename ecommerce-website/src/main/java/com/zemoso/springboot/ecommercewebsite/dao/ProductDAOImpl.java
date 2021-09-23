package com.zemoso.springboot.ecommercewebsite.dao;

import com.zemoso.springboot.ecommercewebsite.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAOImpl implements ProductDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Product> findAll() {
        Query query = entityManager.createQuery("from Product");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Optional<Product> findById(int id) {
        Product product = entityManager.find(Product.class,id);
        return Optional.ofNullable(product);
    }

    @Override
    @Transactional
    public void save(Product product) {
        Product dbProduct = entityManager.merge(product);
        product.setId(dbProduct.getId());
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Product where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
}