package com.mybootapp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.OutwardRegister;
import com.mybootapp.main.repository.OutwardRegisterRepository;

@Service
public class OutwardRegisterService {

	@Autowired
	private OutwardRegisterRepository outwardRegisterRepository;

	public OutwardRegister insert(OutwardRegister outwardRegister) {
		return outwardRegisterRepository.save(outwardRegister);
	}

	public List<OutwardRegister> getAll() {
		return outwardRegisterRepository.findAll();
	}
	
}
