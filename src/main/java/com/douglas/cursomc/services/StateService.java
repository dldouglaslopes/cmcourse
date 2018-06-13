package com.douglas.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.State;
import com.douglas.cursomc.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository repository;

	public List<State> findAll() {
		return  repository.findAllByOrderByName();	
	}
}
