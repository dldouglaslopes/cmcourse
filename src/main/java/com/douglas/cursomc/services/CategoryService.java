package com.douglas.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Category;
import com.douglas.cursomc.repositories.CategoryRepository;
import com.douglas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	
	public Category find (Integer id) {
		Optional<Category> category = repository.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + Category.class.getName()));		
	}
	
	public Category insert(Category category) {
		category.setId(null);
		
		return repository.save(category);
	}
}
