package com.douglas.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Category;
import com.douglas.cursomc.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	
	public Category search(Integer id) {
		Optional<Category> category = repository.findById(id);
		
		return category.orElse(null);		
	}
}
