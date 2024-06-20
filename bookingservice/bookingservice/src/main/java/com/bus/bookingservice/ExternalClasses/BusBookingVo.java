package com.bus.bookingservice.ExternalClasses;

import com.bus.bookingservice.model.BookingModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusBookingVo {

	private BusModel busModel;
	private BookingModel bookingModel;
	private LoginModel loginModel;

}