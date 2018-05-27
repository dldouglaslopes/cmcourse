package com.douglas.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.PaymentBill;

@Service
public class BillService {

	public void fillPaymentBill(PaymentBill bill, Date instant) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instant);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		bill.setDateDue(calendar.getTime());
	}	
}
