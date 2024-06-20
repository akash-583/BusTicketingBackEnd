package com.bus.booking_service.service;

import java.util.List;

import com.bus.booking_service.ExternalClasses.BusBookingVo;
import com.bus.booking_service.model.BookingModel;

public interface BookingService {

	 public BookingModel bookTicket(BookingModel booking);
	 public String cancelTicket(String ticketNo);
	 public List<BookingModel> getAllBookings();
	 public BookingModel getBookingByticketNo(String ticketNo);
	 public BusBookingVo getTicketDetailsWithBusDetails(String ticketNo);
	 public List<BookingModel> getBookingByUsername(String username);

}
