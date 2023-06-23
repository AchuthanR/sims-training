package com.mybootapp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.Manager;
import com.mybootapp.main.model.User;
import com.mybootapp.main.service.ManagerService;
import com.mybootapp.main.service.UserService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@PostMapping("/add")
	public Manager postManager(@RequestBody Manager manager) {
		User user = manager.getUser();
		user.setRole("MANAGER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.insert(user);
        manager.setUser(user);
		return managerService.insert(manager);
	}
	
	@GetMapping("/all")
	public List<Manager> getAll() {
		return managerService.getAll();
	}
	
}
