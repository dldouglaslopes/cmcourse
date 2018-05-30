package com.douglas.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PurchaseOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "address_delivery_id")
	private Address addressDelivery;
	
	@OneToMany(mappedBy = "id.order")
	private Set<ItemOrder> itemOrders = new HashSet<>();
	
	public PurchaseOrder() {}

	public PurchaseOrder(Integer id, Date instant, Client client, Address addressDelivery) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.addressDelivery = addressDelivery;
	}

	public double getTotalValue() {
		double soma = 0.0;
		
		for (ItemOrder itemOrder : itemOrders) {
			soma = soma + itemOrder.getSubTotal();
		}
		
		return soma;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddress() {
		return addressDelivery;
	}

	public void setAddress(Address addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

	public Set<ItemOrder> getItemOrders() {
		return itemOrders;
	}

	public void setItemOrders(Set<ItemOrder> itemOrders) {
		this.itemOrders = itemOrders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseOrder other = (PurchaseOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Order number: ");
		builder.append(getId());
		builder.append(", Instant: ");
		builder.append(dateFormat.format(getInstant()));
		builder.append(", Client: ");
		builder.append(getClient().getName());
		builder.append(", Status payment: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetails: \n");
		
		for (ItemOrder itemOrder : getItemOrders()) {
			builder.append(itemOrder.toString());
		}
		
		builder.append("Total value: ");
		builder.append(format.format(getTotalValue()));
		
		return builder.toString();
	}
	
	
}
