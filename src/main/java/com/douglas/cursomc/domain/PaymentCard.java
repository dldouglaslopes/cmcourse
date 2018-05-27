package com.douglas.cursomc.domain;

import javax.persistence.Entity;

import com.douglas.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentCard")
public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer numInstallment;
	
	public PaymentCard() {}

	public PaymentCard(Integer id, PaymentStatus status, PurchaseOrder order, Integer numInstallment) {
		super(id, status, order);
		this.numInstallment = numInstallment;
	}

	public Integer getNumInstallment() {
		return numInstallment;
	}

	public void setNumInstallment(Integer numInstallment) {
		this.numInstallment = numInstallment;
	}
	
	
}
