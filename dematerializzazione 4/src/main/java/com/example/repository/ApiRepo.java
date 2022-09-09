package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ApiModel;

@Repository
public interface ApiRepo extends CrudRepository<ApiModel, Integer>{
	
}