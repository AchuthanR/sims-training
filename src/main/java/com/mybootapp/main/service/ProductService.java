package com.mybootapp.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Product;
import com.mybootapp.main.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product insert(Product product) {
		return productRepository.save(product);
	}

	public Product getById(int productId) {
		Optional<Product> optional = productRepository.findById(productId);
		
		if (optional.isEmpty()) {
			return null;
		}
		
		return optional.get();
	}

}
