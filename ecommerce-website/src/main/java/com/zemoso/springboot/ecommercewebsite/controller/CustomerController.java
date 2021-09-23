package com.zemoso.springboot.ecommercewebsite.controller;

import com.zemoso.springboot.ecommercewebsite.config.CustomUserDetails;
import com.zemoso.springboot.ecommercewebsite.entity.Customer;
import com.zemoso.springboot.ecommercewebsite.entity.Product;
import com.zemoso.springboot.ecommercewebsite.service.CustomerService;
import com.zemoso.springboot.ecommercewebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String listCustomers(Model theModel){
        List<Customer> customers = customerService.findAll();
        theModel.addAttribute("customers",customers);
        return "customer/customers-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Customer customer = new Customer();
        theModel.addAttribute("customer",customer);
        return "customer/customerForm";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        return "customer/account-page";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model theModel){
        Customer customer = customerService.findById(id);
        theModel.addAttribute("customer",customer);
        return "customer/customerForm";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){
        customerService.deleteById(id);
        return "redirect:/customers/list";
    }

    @GetMapping("/showMyPage")
    public String showMyPage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
        String emailId = userDetails.getUsername();
        Customer customer = customerService.findByEmail(emailId);
        model.addAttribute("customer",customer);
        return "customer-page";
    }

    @GetMapping("/showAccount")
    public String showAccountPage(@RequestParam("customerId") int id,Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "customer/account-page";
    }

    @GetMapping("/showMyCart")
    public String viewMyCart(@RequestParam("customerId") int id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "customer/my-cart";
    }

    @GetMapping("/showAllProducts")
    public String showAllProducts(@RequestParam("customerId") int id, Model model){
        Customer customer = customerService.findById(id);
        List<Product> products = productService.findAll();
        model.addAttribute("customer",customer);
        model.addAttribute("products",products);
        return "product/products-sale";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("customerId") int customerId,@RequestParam("productId") int productId,Model model){
        Customer customer = customerService.findById(customerId);
        Product product = productService.findById(productId);
        if (!customer.isPresentProduct(product)) {
            customer.addProduct(product);
        }
        customerService.save(customer);
        List<Product> products = productService.findAll();
        model.addAttribute("customer",customer);
        model.addAttribute("products",products);
        return "product/products-sale";
    }

    @GetMapping("/deleteProductFromCart")
    public String deleteFromCart(@RequestParam("customerId") int customerId,@RequestParam("productId") int productId,Model model){
        Customer customer = customerService.findById(customerId);
        Product product = productService.findById(productId);
        customer.removeProduct(product);
        customerService.save(customer);
        model.addAttribute("customer",customer);
        model.addAttribute("product",product);
        return "/buy-success";
    }

    @GetMapping("/onsuccessfulbuy")
    public String showSuccessBuyPage(@RequestParam("customerId") int customerId,@RequestParam("productId") int productId,Model model){
        Customer customer = customerService.findById(customerId);
        Product product = productService.findById(productId);
        model.addAttribute(customer);
        model.addAttribute(product);
        return "/buy-success";
    }


}
