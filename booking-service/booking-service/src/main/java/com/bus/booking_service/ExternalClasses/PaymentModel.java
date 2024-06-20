package com.bus.booking_service.ExternalClasses;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
	

	@Id
	private int transactionId;
	private String bookingId;
	private String username;
	private double amount;
	private String transactionStatus;

}


