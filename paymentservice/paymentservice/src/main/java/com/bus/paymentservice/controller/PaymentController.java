package com.bus.paymentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.paymentservice.Sevices.PaymentService;
import com.bus.paymentservice.exception.PaymentNotFoundWithIdException;
import com.bus.paymentservice.model.Payment;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService service;
	
	@GetMapping("/doPayment/{userName}/{bookingId}/{amount}")
	public Payment doPayment(@PathVariable String userName,@PathVariable String bookingId,@PathVariable double amount) throws Exception {
		return service.doPayment(userName,bookingId,amount);
	}
	@GetMapping("/getByTransactionId/{bookingId}")
	public ResponseEntity<Payment> getPayment(@PathVariable String bookingId) throws PaymentNotFoundWithIdException {
		Payment payment= service.getPayment(bookingId);
		return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	}
	@GetMapping("/getAllPayment")
	public List<Payment> getAllPayments()
	{
		List<Payment> paymentlist=service.getallpayment();
		return paymentlist;
	}
    @DeleteMapping("/deletePayment/{bookingId}")
    public ResponseEntity<String>  deletePayment(@PathVariable String bookingId){
		return ResponseEntity.ok(service.deletePayment(bookingId));
	}
    
    @PutMapping("/updatePayment/{bookingId}/{status}")
    public ResponseEntity<String> updatePayment(@PathVariable String bookingId,@PathVariable String status){
		return ResponseEntity.ok(service.updatePayment(bookingId, status));
    }
    
	


}
