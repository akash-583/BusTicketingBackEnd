package com.bus.loginservice.proxy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bus.loginservice.model.PaymentModel;
@FeignClient(name="PaymentService")
public interface PaymentProxy {

	
	@GetMapping("payment/doPayment/{userName}/{bookingId}/{amount}")
	public PaymentModel doPayment(@PathVariable String userName,@PathVariable String bookingId,@PathVariable double amount);
}