package com.zemoso.springboot.ecommercewebsite.controller;

import com.zemoso.springboot.ecommercewebsite.entity.Product;
import com.zemoso.springboot.ecommercewebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String listProducts(Model theModel){
        List<Product> products = productService.findAll();
        theModel.addAttribute("products",products);
        return "product/product-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Product product = new Product();
        theModel.addAttribute("product",product);
        return "product/productForm";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:/products/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId") int id, Model theModel){
        Product product = productService.findById(id);
        theModel.addAttribute("product",product);
        return "product/productForm";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") int id){
        productService.deleteById(id);
        return "redirect:/products/list";
    }


}
