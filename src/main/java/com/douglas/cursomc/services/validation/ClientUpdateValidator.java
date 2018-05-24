package com.douglas.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.dto.ClientDTO;
import com.douglas.cursomc.repositories.ClientRepository;
import com.douglas.cursomc.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO>{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository clientRepository ;
	
	@Override
	public void initialize(ClientUpdate clientInsert) {
	}
	
	@Override
	public boolean isValid(ClientDTO value, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> fieldMessages = new ArrayList<>();

		Client client = clientRepository.findByEmail(value.getEmail());
		
		if (client != null && !client.getId().equals(uriId)) {
			fieldMessages.add(new FieldMessage("type", "Existing email."));
		}
		
		for (FieldMessage fieldMessage : fieldMessages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName())
					.addConstraintViolation();
		}
		
		return fieldMessages.isEmpty();
	}

}
