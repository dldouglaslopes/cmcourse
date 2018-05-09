package com.douglas.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.cursomc.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Category> list() {
		Category categoryA = new Category(1, "Informatics");	
		Category categoryB = new Category(2, "Office");
		
		List<Category> categories = new ArrayList<>();
		categories.add(categoryA);
		categories.add(categoryB);		
		
		return categories;
	}
}
