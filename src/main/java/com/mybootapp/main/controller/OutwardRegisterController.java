package com.mybootapp.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.dto.OutwardRegisterDto;
import com.mybootapp.main.model.Godown;
import com.mybootapp.main.model.OutwardRegister;
import com.mybootapp.main.model.Product;
import com.mybootapp.main.service.GodownService;
import com.mybootapp.main.service.OutwardRegisterService;
import com.mybootapp.main.service.ProductService;

@RestController
@RequestMapping("/outwardregister")
public class OutwardRegisterController {

	@Autowired
	private ProductService productService;

	@Autowired
	private GodownService godownService;

	@Autowired
	private OutwardRegisterService outwardRegisterService;

	@PostMapping("/add/{productId}/{godownId}")
	public ResponseEntity<?> postInwardRegister(@RequestBody OutwardRegister outwardRegister,
			@PathVariable("productId") int productId, @PathVariable("godownId") int godownId) {
		Product product = productService.getById(productId);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product ID given");
		}
		
		Godown godown = godownService.getById(godownId);
		if (godown == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid godown ID given");
		}
		
		outwardRegister.setProduct(product);
		outwardRegister.setGodown(godown);

		outwardRegister.setDateOfDelivery(LocalDate.now());

		outwardRegister = outwardRegisterService.insert(outwardRegister);
		return ResponseEntity.status(HttpStatus.OK).body(outwardRegister);
	}
	
	@GetMapping("/report")
	public List<OutwardRegisterDto> inwardReport() {
		List<OutwardRegister> list = outwardRegisterService.getAll();
		List<OutwardRegisterDto> listDto = new ArrayList<>();
		list.stream().forEach(entry -> {
			OutwardRegisterDto dto = new OutwardRegisterDto();
			dto.setProductTitle(entry.getProduct().getTitle());
			dto.setProductQuantity(entry.getQuantity());
			dto.setGodownLocation(entry.getGodown().getLocation());
			dto.setGodownManager(entry.getGodown().getManager().getName());
			dto.setQuantity(entry.getQuantity());
			dto.setInvoiceNumber(entry.getInvoiceNumber());
			dto.setPurpose(entry.getPurpose());
			dto.setReceiptNo(entry.getReceiptNo());
			dto.setBillValue(entry.getBillValue());
			dto.setDeliveredTo(entry.getDeliveredTo());
			dto.setDateOfDelivery(entry.getDateOfDelivery());
			listDto.add(dto);
		});
		
		return listDto;
	}

}