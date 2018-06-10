package com.douglas.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.domain.PurchaseOrder;

public interface EmailService {
	
	void sendOrderConfirmationEmail(PurchaseOrder order);
	void sendEmail(SimpleMailMessage message);
	void sendOrderConfirmationHtmlEmail(PurchaseOrder order);
	void sendHtmlEmail(MimeMessage message);
	void sendNewPasswordEmail(Client client, String newPwd);
}
