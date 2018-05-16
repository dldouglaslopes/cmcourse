package com.douglas.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.douglas.cursomc.domain.Address;
import com.douglas.cursomc.domain.Category;
import com.douglas.cursomc.domain.City;
import com.douglas.cursomc.domain.Client;
import com.douglas.cursomc.domain.ItemOrder;
import com.douglas.cursomc.domain.Payment;
import com.douglas.cursomc.domain.PaymentBill;
import com.douglas.cursomc.domain.PaymentCard;
import com.douglas.cursomc.domain.Product;
import com.douglas.cursomc.domain.PurchaseOrder;
import com.douglas.cursomc.domain.State;
import com.douglas.cursomc.domain.enums.AddressRepository;
import com.douglas.cursomc.domain.enums.PaymentStatus;
import com.douglas.cursomc.domain.enums.TypeClient;
import com.douglas.cursomc.repositories.CategoryRepository;
import com.douglas.cursomc.repositories.CityRepository;
import com.douglas.cursomc.repositories.ClientRepository;
import com.douglas.cursomc.repositories.ItemOrderRepository;
import com.douglas.cursomc.repositories.OrderRepository;
import com.douglas.cursomc.repositories.PaymentRepository;
import com.douglas.cursomc.repositories.ProductRepository;
import com.douglas.cursomc.repositories.StateRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientRepository clientRepository;	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category categoryA = new Category(null, "Informatics");
		Category categoryB = new Category(null, "Office");
		
		Product productA = new Product(null, "Laptop", 2000.00);
		Product productB = new Product(null, "Printer", 800.00);
		Product productC = new Product(null, "Mouse", 80.00);
		
		categoryA.getProducts().addAll(Arrays.asList(productA, productB, productC));
		categoryB.getProducts().addAll(Arrays.asList(productB));
		
		productA.getCategories().addAll(Arrays.asList(categoryA));
		productB.getCategories().addAll(Arrays.asList(categoryA, categoryB));
		productC.getCategories().addAll(Arrays.asList(categoryA));
		
		categoryRepository.saveAll(Arrays.asList(categoryA, categoryB));
		productRepository.saveAll(Arrays.asList(productA, productB, productC));
	
		State stateA = new State(null, "Minas Gerais");
		State stateB = new State(null, "São Paulo");
		
		City cityA = new City(null, "Uberlândia", stateA);
		City cityB = new City(null, "São Paulo", stateB);
		City cityC = new City(null, "Campinas", stateB);
		
		stateA.getCities().addAll(Arrays.asList(cityA));
		stateB.getCities().addAll(Arrays.asList(cityB, cityC));
		
		stateRepository.saveAll(Arrays.asList(stateA, stateB));
		cityRepository.saveAll(Arrays.asList(cityA, cityB, cityC));
		
		Client clientA = new Client(null, "Maria Silva", "maria@gmail.com", "325465", TypeClient.PRIVATEINDIVIDUAL);
		clientA.getPhones().addAll(Arrays.asList("45646445", "54564566"));
		
		Address addressA = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", clientA, cityA);
		Address addressB = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38220834", clientA, cityB);
		
		clientA.getAddresses().addAll(Arrays.asList(addressA, addressB));
		
		clientRepository.saveAll(Arrays.asList(clientA));
		addressRepository.saveAll(Arrays.asList(addressA, addressB));
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		PurchaseOrder orderA = new PurchaseOrder(null, format.parse("30/09/2017 10:32"), clientA, addressA);
		PurchaseOrder orderB = new PurchaseOrder(null, format.parse("10/10/2017 19:35"), clientA, addressB);
		
		Payment paymentA = new PaymentCard(null, PaymentStatus.PAID, orderA, 6);
		orderA.setPayment(paymentA);
		
		Payment paymentB = new PaymentBill(null, PaymentStatus.PENDING, orderB, format.parse("20/10/2017 00:00"), null);
		orderB.setPayment(paymentB);
		
		clientA.getOrders().addAll(Arrays.asList(orderA, orderB));
		
		orderRepository.saveAll(Arrays.asList(orderA, orderB));
		paymentRepository.saveAll(Arrays.asList(paymentA, paymentB));
	
		ItemOrder itemOrderA = new ItemOrder(orderA, productA, 0.00, 1, 2000.00);
		ItemOrder itemOrderB = new ItemOrder(orderA, productC, 0.00, 2, 80.00);
		ItemOrder itemOrderC = new ItemOrder(orderB, productB, 100.00, 1, 800.00);
			
		orderA.getItemOrders().addAll(Arrays.asList(itemOrderA, itemOrderB));
		orderB.getItemOrders().addAll(Arrays.asList(itemOrderC));
		
		productA.getItemOrders().addAll(Arrays.asList(itemOrderA));
		productB.getItemOrders().addAll(Arrays.asList(itemOrderC));
		productC.getItemOrders().addAll(Arrays.asList(itemOrderB));
		
		itemOrderRepository.saveAll(Arrays.asList(itemOrderA, itemOrderB, itemOrderC));
	}
}
