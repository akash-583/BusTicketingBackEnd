package com.bus.bookingservice.service;

import java.util.List;

import com.bus.bookingservice.ExternalClasses.BusBookingVo;
import com.bus.bookingservice.model.BookingModel;

public interface BookingService {
	 public BookingModel bookTicket(BookingModel booking);
	 public String cancelTicket(String ticketNo);
	 public List<BookingModel> getAllBookings();
	 public BookingModel getBookingByTicketNo(String ticketNo);
	 public BusBookingVo getTicketDetailsWithBusDetails(String ticketNo);
	 public List<BookingModel> getBookingByUsername(String username);
}
