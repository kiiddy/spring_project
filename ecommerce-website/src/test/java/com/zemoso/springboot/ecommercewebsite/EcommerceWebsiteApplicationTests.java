package com.zemoso.springboot.ecommercewebsite;

import com.zemoso.springboot.ecommercewebsite.dao.ProductDAO;
import com.zemoso.springboot.ecommercewebsite.entity.Product;
import com.zemoso.springboot.ecommercewebsite.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EcommerceWebsiteApplicationTests {

	@InjectMocks
	ProductServiceImpl service;

	@Mock
	ProductDAO dao;

	@BeforeTestExecution
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void findAllTest(){
		List<Product> list = new ArrayList<>();
		Product p1 = new Product(1,"Spinz powder",99,1);
		Product p2 = new Product(2,"Boroplus powder",59,1);
		Product p3 = new Product(3,"ponds powder",79,1);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		when(dao.findAll()).thenReturn(list);

		//test
		List<Product> productList = service.findAll();

		assertEquals(3, productList.size());
		verify(dao, times(1)).findAll();
	}

	@Test
	void findByIdTest()
	{
		when(dao.findById(1)).thenReturn(java.util.Optional.of(new Product(1, "Santoor powder", 89, 1)));
		Product product = service.findById(1);

		assertEquals("Santoor powder", product.getName());
		assertEquals(89, product.getPrice());
		assertEquals(1, product.getQuantity());
	}

	@Test
	void saveTest()
	{
		Product product = new Product(1,"Santoor powder",89,1);

		service.save(product);

		verify(dao, times(1)).save(product);
	}

}
