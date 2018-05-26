package com.douglas.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Category;
import com.douglas.cursomc.domain.Product;
import com.douglas.cursomc.repositories.CategoryRepository;
import com.douglas.cursomc.repositories.ProductRepository;
import com.douglas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Product find (Integer id) {
		Optional<Product> product = repository.findById(id);
		
		return product.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + Product.class.getName()));		
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return repository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
