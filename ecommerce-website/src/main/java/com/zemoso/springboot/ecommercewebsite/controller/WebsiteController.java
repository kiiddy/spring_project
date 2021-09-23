package com.zemoso.springboot.ecommercewebsite.controller;

import com.zemoso.springboot.ecommercewebsite.entity.Customer;
import com.zemoso.springboot.ecommercewebsite.service.CustomerService;
import com.zemoso.springboot.ecommercewebsite.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebsiteController {


   // private static final Logger log = LoggerFactory.getLogger(WebsiteController.class);

    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String viewHomePage(){
        return "home-page";
    }

    @GetMapping("/showRegistrationPage")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "signup-form";
    }

    @PostMapping("/register")
    public String processRegister(@Valid Customer customer, BindingResult bindingResult, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        if (customerService.customerExists(customer.getEmail())){
            bindingResult.addError(new FieldError("customer","email","Email address already exists"));
        }
        if (bindingResult.hasErrors()){
            return "signup-form";
        }
        customerService.save(customer);

        return "register-success";
    }

    @GetMapping("/customers-list")
    public String listCustomers(Model theModel){
        List<Customer> customers = customerService.findAll();
        theModel.addAttribute("customers",customers);
        return "customers-list";
    }

}
