package com.mybootapp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.User;
import com.mybootapp.main.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/add")
	public User add(@RequestBody User user) {
		user.setRole("ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.insert(user);
	}
	
}
