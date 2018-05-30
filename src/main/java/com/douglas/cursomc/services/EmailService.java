package com.douglas.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.douglas.cursomc.domain.PurchaseOrder;

public interface EmailService {
	
	void sendOrderConfirmationEmail(PurchaseOrder order);
	void sendEmail(SimpleMailMessage message);
}
