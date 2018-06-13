package com.douglas.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.cursomc.domain.City;
import com.douglas.cursomc.domain.State;
import com.douglas.cursomc.dto.CityDTO;
import com.douglas.cursomc.dto.StateDTO;
import com.douglas.cursomc.services.CityService;
import com.douglas.cursomc.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {

	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		List<State> states = service.findAll();
		List<StateDTO> stateDTOs = states.stream().map(state -> new StateDTO(state)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(stateDTOs);
	}
	
	@RequestMapping(value= "/{stateId}/cities" , method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
		List<City> cities = cityService.findByState(stateId);
		List<CityDTO> cityDTOs = cities.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(cityDTOs);
	}
}
