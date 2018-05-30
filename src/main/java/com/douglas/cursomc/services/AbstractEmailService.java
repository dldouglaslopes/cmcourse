package com.douglas.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.douglas.cursomc.domain.PurchaseOrder;

public abstract class AbstractEmailService implements EmailService{
		
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(PurchaseOrder order) {
		SimpleMailMessage message = prepareSimpleMailFromOrder(order);
		sendEmail(message);
	}

	protected SimpleMailMessage prepareSimpleMailFromOrder(PurchaseOrder order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(order.getClient().getEmail());
		message.setFrom(order.getClient().getEmail());		
		message.setSubject("Confirmed Order! Code: " + order.getId());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(order.toString());
		
		return message;
	}
}
