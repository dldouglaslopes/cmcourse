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
import com.douglas.cursomc.domain.enums.PaymentStatus;
import com.douglas.cursomc.domain.enums.TypeClient;
import com.douglas.cursomc.repositories.AddressRepository;
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
		Category category1 = new Category(null, "Informatics");
		Category category2 = new Category(null, "Office");
		Category category3 = new Category(null, "Home");
		Category category4 = new Category(null, "Eletronics");
		Category category5 = new Category(null, "Decoration");
		Category category6 = new Category(null, "Home");
		Category category7 = new Category(null, "Perfumery");
		
		Product product1 = new Product(null, "Laptop", 2000.00);
		Product product2 = new Product(null, "Printer", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);
		Product product4 = new Product(null, "Desk", 300.00);
		Product product5 = new Product(null, "Towel", 50.00);
		Product product6 = new Product(null, "Quilt", 200.00);
		Product product7 = new Product(null, "TV", 1200.00);
		Product product8 = new Product(null, "Table", 800.00);
		Product product9 = new Product(null, "Abajour", 100.00);
		Product product10 = new Product(null, "Comb", 180.00);
		Product product11 = new Product(null, "Shampoo", 90.00);
		
		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product4, product4));
		category3.getProducts().addAll(Arrays.asList(product5, product6));
		category4.getProducts().addAll(Arrays.asList(product1, product2, product3, product7));
		category5.getProducts().addAll(Arrays.asList(product8));
		category6.getProducts().addAll(Arrays.asList(product9, product10));
		category7.getProducts().addAll(Arrays.asList(product11));
		
		product1.getCategories().addAll(Arrays.asList(category1, category4));
		product2.getCategories().addAll(Arrays.asList(category1, category2, category4));
		product3.getCategories().addAll(Arrays.asList(category1, category4));
		product4.getCategories().addAll(Arrays.asList(category2));
		product5.getCategories().addAll(Arrays.asList(category3));
		product6.getCategories().addAll(Arrays.asList(category3));
		product7.getCategories().addAll(Arrays.asList(category4));
		product8.getCategories().addAll(Arrays.asList(category5));
		product9.getCategories().addAll(Arrays.asList(category6));
		product10.getCategories().addAll(Arrays.asList(category6));
		product11.getCategories().addAll(Arrays.asList(category7));
		
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5, category6, category7));
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11));
	
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
	
		ItemOrder itemOrderA = new ItemOrder(orderA, product1, 0.00, 1, 2000.00);
		ItemOrder itemOrderB = new ItemOrder(orderA, product3, 0.00, 2, 80.00);
		ItemOrder itemOrderC = new ItemOrder(orderB, product2, 100.00, 1, 800.00);
			
		orderA.getItemOrders().addAll(Arrays.asList(itemOrderA, itemOrderB));
		orderB.getItemOrders().addAll(Arrays.asList(itemOrderC));
		
		product1.getItemOrders().addAll(Arrays.asList(itemOrderA));
		product2.getItemOrders().addAll(Arrays.asList(itemOrderC));
		product3.getItemOrders().addAll(Arrays.asList(itemOrderB));
		
		itemOrderRepository.saveAll(Arrays.asList(itemOrderA, itemOrderB, itemOrderC));
	}
}
