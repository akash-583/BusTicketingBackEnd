package com.bus.paymentservice.Sevices;

import java.util.List;

import com.bus.paymentservice.exception.PaymentFailException;
import com.bus.paymentservice.exception.PaymentNotFoundWithIdException;
import com.bus.paymentservice.model.Payment;

public interface PaymentService {

	 Payment doPayment(String userName,String bookingId,double amount) throws PaymentFailException;
		Payment getPayment(String bookingId) throws PaymentNotFoundWithIdException;
	    List<Payment> getallpayment();
	    String deletePayment(String bookingId);
	    String updatePayment(String bookingId,String status);
}
