package com.douglas.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.repositories.ClientRepository;
import com.douglas.cursomc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = clientRepository.findByEmail(username);
		
		if (client == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
	}

}
