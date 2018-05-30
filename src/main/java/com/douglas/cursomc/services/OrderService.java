package com.douglas.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.ItemOrder;
import com.douglas.cursomc.domain.PaymentBill;
import com.douglas.cursomc.domain.PurchaseOrder;
import com.douglas.cursomc.domain.enums.PaymentStatus;
import com.douglas.cursomc.repositories.ItemOrderRepository;
import com.douglas.cursomc.repositories.OrderRepository;
import com.douglas.cursomc.repositories.PaymentRepository;
import com.douglas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private BillService billService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
	@Autowired
	private ClientService clientService;
	
	public PurchaseOrder find (Integer id) {
		Optional<PurchaseOrder> order = repository.findById(id);
		
		return order.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Tipo: " + PurchaseOrder.class.getName()));		
	}
	
	@Transactional
	public PurchaseOrder insert(PurchaseOrder order) {
		order.setId(null);
		order.setClient(clientService.find(order.getClient().getId()));
		order.setInstant(new Date());
		order.getPayment().setStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);
		
		if (order.getPayment() instanceof PaymentBill) {
			PaymentBill bill = (PaymentBill) order.getPayment();
			billService.fillPaymentBill(bill, order.getInstant());
		}
		
		order = repository.save(order);
		paymentRepository.save(order.getPayment());
		
		for (ItemOrder itemOrder : order.getItemOrders()) {
			itemOrder.setDiscount(0.0);
			itemOrder.setProduct(productService.find(itemOrder.getProduct().getId()));
			itemOrder.setPrice(itemOrder.getProduct().getPrice());
			itemOrder.setOrder(order);
		}
		
		itemOrderRepository.saveAll(order.getItemOrders());
		System.out.println(order);
		
		return order;
	}
}
