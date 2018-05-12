package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.douglas.cursomc.domain.City;

@Repository
public interface CityRepository extends JpaRepository< City, Integer>{
	
}
