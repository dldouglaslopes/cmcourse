package com.douglas.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.douglas.cursomc.domain.enums.Profile;
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

@Service
public class DBService {
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
	@Autowired
	private BCryptPasswordEncoder encoder;	
	
	public void instatiateTestDatabase() throws ParseException {
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
		
		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);
		category1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		p12.getCategories().add(category1);
		p13.getCategories().add(category1);
		p14.getCategories().add(category1);
		p15.getCategories().add(category1);
		p16.getCategories().add(category1);
		p17.getCategories().add(category1);
		p18.getCategories().add(category1);
		p19.getCategories().add(category1);
		p20.getCategories().add(category1);
		p21.getCategories().add(category1);
		p22.getCategories().add(category1);
		p23.getCategories().add(category1);
		p24.getCategories().add(category1);
		p25.getCategories().add(category1);
		p26.getCategories().add(category1);
		p27.getCategories().add(category1);
		p28.getCategories().add(category1);
		p29.getCategories().add(category1);
		p30.getCategories().add(category1);
		p31.getCategories().add(category1);
		p32.getCategories().add(category1);
		p33.getCategories().add(category1);
		p34.getCategories().add(category1);
		p35.getCategories().add(category1);
		p36.getCategories().add(category1);
		p37.getCategories().add(category1);
		p38.getCategories().add(category1);
		p39.getCategories().add(category1);
		p40.getCategories().add(category1);
		p41.getCategories().add(category1);
		p42.getCategories().add(category1);
		p43.getCategories().add(category1);
		p44.getCategories().add(category1);
		p45.getCategories().add(category1);
		p46.getCategories().add(category1);
		p47.getCategories().add(category1);
		p48.getCategories().add(category1);
		p49.getCategories().add(category1);
		p50.getCategories().add(category1);
		
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
		productRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		State stateA = new State(null, "Minas Gerais");
		State stateB = new State(null, "São Paulo");
		
		City cityA = new City(null, "Uberlândia", stateA);
		City cityB = new City(null, "São Paulo", stateB);
		City cityC = new City(null, "Campinas", stateB);
		
		stateA.getCities().addAll(Arrays.asList(cityA));
		stateB.getCities().addAll(Arrays.asList(cityB, cityC));
		
		stateRepository.saveAll(Arrays.asList(stateA, stateB));
		cityRepository.saveAll(Arrays.asList(cityA, cityB, cityC));
		
		Client clientA = new Client(null, "Maria Silva", "dldouglaslopes@gmail.com", "36378912377", TypeClient.PRIVATEINDIVIDUAL, encoder.encode("123"));
		clientA.getPhones().addAll(Arrays.asList("45646445", "54564566"));

		Client clientB = new Client(null, "Ana Costa", "dldouglaslopes@yahoo.com", "31628382740", TypeClient.PRIVATEINDIVIDUAL, encoder.encode("123"));
		clientB.addProfile(Profile.ADMIN);
		clientB.getPhones().addAll(Arrays.asList("45641111", "54564333"));
		
		Address addressA = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", clientA, cityA);
		Address addressB = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38278834", clientA, cityB);
		Address addressC = new Address(null, "Avenida Floriano", "2106", null, "Centro", "38254834", clientB, cityB);
		
		clientA.getAddresses().addAll(Arrays.asList(addressA, addressB));
		clientB.getAddresses().addAll(Arrays.asList(addressC));
		
		clientRepository.saveAll(Arrays.asList(clientA, clientB));
		addressRepository.saveAll(Arrays.asList(addressA, addressB, addressC));
		
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
