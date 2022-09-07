package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Fattura;

	
	public interface ApiRepo extends CrudRepository<Fattura, Integer> {
		
		
	}
