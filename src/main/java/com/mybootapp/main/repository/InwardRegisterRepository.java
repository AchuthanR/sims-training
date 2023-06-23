package com.mybootapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mybootapp.main.model.InwardRegister;

public interface InwardRegisterRepository extends JpaRepository<InwardRegister, Integer> {

	@Query("select ir from InwardRegister ir where ir.product.id=?1 AND ir.quantity >= ?2")
	InwardRegister checkQuantity(int productId, int quantityPuchased);
	
}
