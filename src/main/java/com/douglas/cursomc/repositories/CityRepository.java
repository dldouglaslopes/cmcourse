package com.douglas.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.douglas.cursomc.domain.City;

@Repository
public interface CityRepository extends JpaRepository< City, Integer>{
	
	@Query("SELECT obj FROM City obj WHERE obj.state.id = :stateId ORDER BY obj.name")
	public List<City> findCities(@Param("stateId") Integer state_id);
}
