package com.mybootapp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.Godown;
import com.mybootapp.main.model.Manager;
import com.mybootapp.main.service.GodownService;
import com.mybootapp.main.service.ManagerService;

@RestController
@RequestMapping("/godown")
public class GodownController {

	@Autowired
	private GodownService godownService;
	
	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> insertGodown(@PathVariable("managerId") int managerId, @RequestBody Godown godown) {
		Manager manager = managerService.getById(managerId);
		if (manager == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid Manager ID");
		}
		
		godown.setManager(manager);
		
		godown = godownService.insert(godown);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(godown);
	}
	
}
