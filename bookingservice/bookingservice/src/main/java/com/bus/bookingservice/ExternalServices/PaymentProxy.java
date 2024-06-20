package com.bus.bookingservice.ExternalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bus.bookingservice.model.PaymentModel;


@FeignClient(name="PaymentService")
public interface PaymentProxy {
	@GetMapping("payment/doPayment/{userName}/{bookingId}/{amount}")
	public PaymentModel doPayment(@PathVariable String userName,@PathVariable String bookingId,@PathVariable double amount);
}
