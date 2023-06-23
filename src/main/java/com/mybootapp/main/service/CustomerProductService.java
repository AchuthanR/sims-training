package com.mybootapp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.CustomerProduct;
import com.mybootapp.main.repository.CustomerProductRepository;

@Service
public class CustomerProductService {

	@Autowired
	private CustomerProductRepository customerProductRepository;

	public CustomerProduct insert(CustomerProduct customerProduct) {
		return customerProductRepository.save(customerProduct);
	}

}