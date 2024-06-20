package com.bus.paymentservice.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bus.paymentservice.model.Payment;
public interface PaymentRepository extends MongoRepository<Payment,Integer>{
	Payment findByBookingId(String bookingId);
}
