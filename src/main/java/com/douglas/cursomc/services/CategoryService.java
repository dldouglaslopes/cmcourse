package com.douglas.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Category;
import com.douglas.cursomc.dto.CategoryDTO;
import com.douglas.cursomc.repositories.CategoryRepository;
import com.douglas.cursomc.services.exceptions.DataIntegrityException;
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

	public Category update(Category category) {
		find(category.getId());
		repository.save(category);
		
		return null;
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}

	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}
}
