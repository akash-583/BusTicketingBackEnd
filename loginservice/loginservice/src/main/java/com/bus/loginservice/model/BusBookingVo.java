package com.bus.loginservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusBookingVo {

	private BusModel busModel;
	private BookingModel bookingModel;
	private Login loginModel;
}
