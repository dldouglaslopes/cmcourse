package com.douglas.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.repositories.ClientRepository;
import com.douglas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();	
	
	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		
		if (client == null) {
			throw new ObjectNotFoundException("Email not found.");
		}
		
		String newPwd = newPassword();
		client.setPassword(bCryptPasswordEncoder.encode(newPwd));

		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPwd);
	}

	private String newPassword() {
		char[] vet = new char[10];
		
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		}
		else{
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
