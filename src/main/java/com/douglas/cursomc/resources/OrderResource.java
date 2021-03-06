package com.douglas.cursomc.resources;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PurchaseOrder order){
		order = service.insert(order);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(order.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<PurchaseOrder>> findPage(
				@RequestParam(value = "page", defaultValue = "0") Integer page, 
				@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
				@RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
				@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		Page<PurchaseOrder> orders = service.findPage(page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(orders);
	}
}
