package com.douglas.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.City;
import com.douglas.cursomc.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;

	public List<City> findByState(Integer id) {
		return repository.findCities(id);
	}
}
