package com.bus.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bus.paymentservice.externalClassses.BookingModel;




@FeignClient(name = "BOOKINGSERVICE", url="http://localhost:9007/bookings")
public interface BookingProxy {

	@GetMapping("/ViewTicketByUserName/{username}")
    public BookingModel viewByUserName(@PathVariable String username);
	
	@DeleteMapping("/deleteTicket/{ticketNo}")
    public String cancelTicket(@PathVariable String ticketNo);
}