package com.douglas.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.cursomc.domain.Category;
import com.douglas.cursomc.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(value= "/{id}" , method=RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category category = service.find(id);
		
		return ResponseEntity.ok().body(category);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category category) {
		category = service.insert(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Category category) {
		category.setId(id);
		category = service.update(category);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}" , method=RequestMethod.DELETE)
	public ResponseEntity<Category> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
