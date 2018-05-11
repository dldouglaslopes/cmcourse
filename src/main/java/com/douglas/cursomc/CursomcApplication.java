package com.douglas.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.douglas.cursomc.domain.Category;
import com.douglas.cursomc.domain.Product;
import com.douglas.cursomc.repositories.CategoryRepository;
import com.douglas.cursomc.repositories.ProductRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category categoryA = new Category(null, "Informatics");
		Category categoryB = new Category(null, "Office");
		
		Product productA = new Product(null, "Laptop", 2000.00);
		Product productB = new Product(null, "Printer", 800.00);
		Product productC = new Product(null, "Mouse", 80.00);
		
		categoryA.getProducts().addAll(Arrays.asList(productA, productB, productC));
		categoryB.getProducts().addAll(Arrays.asList(productB));
		
		productA.getCategories().addAll(Arrays.asList(categoryA));
		productB.getCategories().addAll(Arrays.asList(categoryA, categoryB));
		productC.getCategories().addAll(Arrays.asList(categoryA));
		
		categoryRepository.saveAll(Arrays.asList(categoryA, categoryB));
		productRepository.saveAll(Arrays.asList(productA, productB, productC));
	}
}
