package com.zemoso.springboot.ecommercewebsite.config;
import com.zemoso.springboot.ecommercewebsite.entity.Customer;
import com.zemoso.springboot.ecommercewebsite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(customer);
    }

}
