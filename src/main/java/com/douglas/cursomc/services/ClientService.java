package com.douglas.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Address;
import com.douglas.cursomc.domain.City;
import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.domain.enums.TypeClient;
import com.douglas.cursomc.dto.ClientDTO;
import com.douglas.cursomc.dto.ClientNewDTO;
import com.douglas.cursomc.repositories.AddressRepository;
import com.douglas.cursomc.repositories.ClientRepository;
import com.douglas.cursomc.services.exceptions.DataIntegrityException;
import com.douglas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Client find (Integer id) {
		Optional<Client> client = repository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + Client.class.getName()));		
	}
	
	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		client = repository.save(client);
		addressRepository.saveAll(client.getAddresses());
		
		return client;
	}
	
	public Client update(Client client) {
		Client newClient = find(client.getId());
		updateData(newClient, client);
		
		return repository.save(newClient);
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete because there are related orders.");
		}
	}

	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO clientNewDTO) {
		Client client = new Client(null, clientNewDTO.getName(), clientNewDTO.getEmail(), clientNewDTO.getCpfOrCnpj(), TypeClient.toEnum(clientNewDTO.getType()));
		City city = new City(clientNewDTO.getCityId(), null, null);
		Address address = new Address(null, clientNewDTO.getPatio(), clientNewDTO.getNumber(), clientNewDTO.getAdditional(), clientNewDTO.getDistrict(), clientNewDTO.getZipCode(), client, city);
		client.getAddresses().add(address);
		client.getPhones().add(clientNewDTO.getPhone1());
		
		if (clientNewDTO.getPhone2() != null) {
			client.getPhones().add(clientNewDTO.getPhone2());
		}
		
		if (clientNewDTO.getPhone3() != null) {
			client.getPhones().add(clientNewDTO.getPhone3());
		}
		
		return client;
	}

	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}	
}
