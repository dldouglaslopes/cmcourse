package com.douglas.cursomc.services;

import java.util.Date;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.douglas.cursomc.domain.PurchaseOrder;

public abstract class AbstractEmailService implements EmailService {
		
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(PurchaseOrder order) {
		SimpleMailMessage message = prepareSimpleMailFromOrder(order);
		sendEmail(message);
	}

	protected SimpleMailMessage prepareSimpleMailFromOrder(PurchaseOrder order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(order.getClient().getEmail());
		message.setFrom(sender);		
		message.setSubject("Confirmed Order! Code: " + order.getId());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(order.toString());
		
		return message;
	}
	
	protected String htmlFromTemplateOrder(PurchaseOrder order) {
		Context context = new Context();
		context.setVariable("order", order);
		
		return templateEngine.process("email/confirmationOrder", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(PurchaseOrder order) {		
		try {
			MimeMessage message = prepareMimeMessageFromOrder(order);
			sendHtmlEmail(message);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(order);
		}
	}

	protected MimeMessage prepareMimeMessageFromOrder(PurchaseOrder order) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(order.getClient().getEmail());
		helper.setFrom(sender);
		helper.setSubject("Order Confirmed! Code: " + order.getId());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplateOrder(order), true);
		
		return mimeMessage;
	}
}
