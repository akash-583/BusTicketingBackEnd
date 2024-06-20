package com.bus.loginservice.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {

	@Id
	private int transactionId;
	private String bookingId;
	private String username;
	private double amount;
	private String transactionStatus;
}
