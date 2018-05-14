package com.douglas.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.repositories.ClientRepository;
import com.douglas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	
	public Client find (Integer id) {
		Optional<Client> category = repository.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + Client.class.getName()));		
	}
}
