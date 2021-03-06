package com.douglas.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.douglas.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentBill")
public class PaymentBill extends Payment{
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateDue;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date datePayment;
	
	public PaymentBill() {}

	public PaymentBill(Integer id, PaymentStatus status, PurchaseOrder order, Date dateDue, Date datePayment) {
		super(id, status, order);
		this.dateDue = dateDue;
		this.datePayment = datePayment;
	}

	public Date getDateDue() {
		return dateDue;
	}

	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}
	
	
}
