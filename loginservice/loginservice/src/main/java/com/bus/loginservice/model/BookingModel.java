package com.bus.loginservice.model;


import lombok.Data;

@Data

public class BookingModel {
	
	
	private String ticketNo;

	private String username;

	private String busNo;

	private String phnnumber;

	private String email;

	private int numberOfTickets;

	private int Cost;

}