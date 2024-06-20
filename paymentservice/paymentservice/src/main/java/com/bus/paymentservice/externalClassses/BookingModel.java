package com.bus.paymentservice.externalClassses;


import org.springframework.data.annotation.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel {
	
	@Id
	private String ticketNo;

	private String username;

	private String busNo;


	private String phnnumber;


	private String email;


	private int numberOfTickets;

	private int Cost;

}