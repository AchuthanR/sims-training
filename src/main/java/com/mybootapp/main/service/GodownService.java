package com.mybootapp.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Godown;
import com.mybootapp.main.repository.GodownRepository;

@Service
public class GodownService {
	
	@Autowired
	private GodownRepository godownRepository;

	public Godown insert(Godown godown) {
		return godownRepository.save(godown);
	}

	public Godown getById(int godownId) {
		Optional<Godown> optional = godownRepository.findById(godownId);
		
		if(optional.isEmpty()) {
			return null; 
		}
		
		return optional.get();
	}
	
}
