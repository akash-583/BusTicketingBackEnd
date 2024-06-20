package com.bus.loginservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bus.loginservice.model.BookingModel;
import com.bus.loginservice.model.BusBookingVo;
import com.bus.loginservice.model.BusModel;

import jakarta.validation.Valid;

@FeignClient(name = "BOOKINGSERVICE", url = "http://localhost:9001/bookings")
public interface BookingProxy {
	@PostMapping("/booking")
	public BookingModel bookTicket(@Valid @RequestBody BookingModel booking);
	
	 @DeleteMapping("/deleteTicket/{ticketNo}")
	    public String cancelTicket(@PathVariable String ticketNo);
	 
	 @GetMapping("/ViewAllBookings")
	    public List<BookingModel> viewAllBookings();
	 
	 @GetMapping("/ViewTicketByTicketNo/{ticketNo}") 
	    public BookingModel viewByticketNo(@PathVariable String ticketNo);
	 
	 @GetMapping("/viewByUserName/{username}")
	    public List<BookingModel> viewByUserName(@PathVariable String username);
	 
	 @GetMapping("/ViewBookingTicketByItsBusAndTotalCost/{ticketNo}")
	 public BusBookingVo getBookingTicketByItsBusAndTotalCost(@PathVariable String ticketNo);
}
