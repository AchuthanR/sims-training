package com.mybootapp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.Employee;
import com.mybootapp.main.model.Manager;
import com.mybootapp.main.model.User;
import com.mybootapp.main.service.EmployeeService;
import com.mybootapp.main.service.ManagerService;
import com.mybootapp.main.service.UserService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> postEmployee(@PathVariable("managerId") int managerId, @RequestBody Employee employee) {
		Manager manager = managerService.getById(managerId);
		if (manager == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Manager ID invalid");
		}
		employee.setManager(manager);
		
		User user = employee.getUser();
		user.setRole("EMPLOYEE");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.insert(user);
        employee.setUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(employeeService.insert(employee));
	}
	
}
