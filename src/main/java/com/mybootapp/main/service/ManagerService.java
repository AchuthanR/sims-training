package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Manager;
import com.mybootapp.main.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	public Manager insert(Manager manager) {
		return managerRepository.save(manager);
	}

	public List<Manager> getAll() {
		return managerRepository.findAll();
	}

	public Manager getById(int managerId) {
		Optional<Manager> managerFound = managerRepository.findById(managerId);

		if (managerFound.isEmpty()) {
			return null;
		}

		return managerFound.get();
	}

}
