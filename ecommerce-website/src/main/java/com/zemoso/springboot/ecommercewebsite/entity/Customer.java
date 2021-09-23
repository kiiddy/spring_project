package com.zemoso.springboot.ecommercewebsite.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Enter your first name")
    @Pattern(regexp = "^[a-zA-Z]*",message = "Only alphabets are allowed")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Enter your last name")
    @Pattern(regexp = "^[a-zA-Z]*",message = "Only alphabets are allowed")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$",message = "Password must contain atleast one uppercase alphabet, atleast one lowercase alphabet, atleast one digit, atleast one special character and minimum 6 in length")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "order1",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;



    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        for (Product p : products){
            if (p.equals(product)) {
                p.setQuantity(1);
            }
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public boolean isPresentProduct(Product product){
        for (Product p : products){
            if (p.equals(product)) {
                p.setQuantity(p.getQuantity()+1);
                return true;
            }
        }
        return false;
    }
}
