package com.bus.bookingservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.bookingservice.ExternalClasses.BusBookingVo;
import com.bus.bookingservice.model.BookingModel;
import com.bus.bookingservice.service.BookingService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@RestController
@RequestMapping("/bookings")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking")
	public BookingModel bookTicket(@Valid @RequestBody BookingModel booking) {
		
		return bookingService.bookTicket(booking);
	}
	
	 @DeleteMapping("/cancelingTicketByTicketNo/{ticketNo}")
	    public String cancelTicket(@PathVariable String ticketNo) {
		
		 return bookingService.cancelTicket(ticketNo);
	 }
	 
	 @GetMapping("/ViewAllBookings")
	    public List<BookingModel> viewAllBookings() {
		 log.info("Retrieving AllBookings Data ");
		 return bookingService.getAllBookings();
	 }
	 
	 @GetMapping("/ViewTicketByTicketNo/{ticketNo}") 
	    public BookingModel viewByticketNo(@PathVariable String ticketNo) {
		 log.info("Retrieving Tickets Data by ticketNo ");
		 return bookingService.getBookingByTicketNo(ticketNo);
	 }
	 
	 @GetMapping("/viewByUserName/{username}")
	    public List<BookingModel> viewByUserName(@PathVariable String username) {
	        log.info("viewByUserName Method Started Inside the Authorize Controller");
	        List<BookingModel> bookings = bookingService.getBookingByUsername(username);
	        return bookings;
	    }
	 @GetMapping("/ViewBookingTicketByItsBusAndTotalCost/{ticketNo}")
	 public BusBookingVo getBookingTicketByItsBusAndTotalCost(@PathVariable String ticketNo) {
		 return bookingService.getTicketDetailsWithBusDetails(ticketNo);
	 }

}
