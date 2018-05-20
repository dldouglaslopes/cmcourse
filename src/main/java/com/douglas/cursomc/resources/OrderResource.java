package com.douglas.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.cursomc.domain.PurchaseOrder;
import com.douglas.cursomc.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(value= "/{id}" , method=RequestMethod.GET)
	public ResponseEntity<PurchaseOrder> find(@PathVariable Integer id) {
		PurchaseOrder order = service.find(id);
		
		return ResponseEntity.ok().body(order);
	}
}