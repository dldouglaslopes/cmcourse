package com.douglas.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.douglas.cursomc.domain.enums.TypeClient;
import com.douglas.cursomc.dto.ClientNewDTO;
import com.douglas.cursomc.resources.exceptions.FieldMessage;
import com.douglas.cursomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>{

	@Override
	public void initialize(ClientInsert clientInsert) {
	}
	
	@Override
	public boolean isValid(ClientNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> fieldMessages = new ArrayList<>();
		
		if (value.getType().equals(TypeClient.PRIVATEINDIVIDUAL.getCode()) && !BR.isValidCPF(value.getCpfOrCnpj())) {
			fieldMessages.add(new FieldMessage("type", "Invalid CPF"));
		}
		
		if (value.getType().equals(TypeClient.LEGALENTITY.getCode()) && !BR.isValidCNPJ(value.getCpfOrCnpj())) {
			fieldMessages.add(new FieldMessage("type", "Invalid CNPJ"));
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
