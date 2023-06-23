package com.mybootapp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.Product;
import com.mybootapp.main.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/*
	 PATH: /add
	 Method: POST
	 RequestBody: Product product
	 Response: product
	 PathVariable: None
	 */
	@PostMapping("/add")
	public Product postProduct(@RequestBody Product product) {
		product = productService.insert(product);
		return product;
	}
	
}
