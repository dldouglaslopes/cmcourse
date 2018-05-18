package com.douglas.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.PurchaseOrder;
import com.douglas.cursomc.repositories.OrderRepository;
import com.douglas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	public PurchaseOrder find (Integer id) {
		Optional<PurchaseOrder> order = repository.findById(id);
		
		return order.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + PurchaseOrder.class.getName()));		
	}
}
