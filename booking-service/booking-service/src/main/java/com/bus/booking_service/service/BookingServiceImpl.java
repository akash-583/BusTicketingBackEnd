package com.bus.booking_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.booking_service.ExternalClasses.BusBookingVo;
import com.bus.booking_service.model.BookingModel;
import com.bus.booking_service.ExternalClasses.BusBookingVo;
import com.bus.booking_service.ExternalClasses.BusModel;
import com.bus.booking_service.ExternalClasses.LoginModel;
import com.bus.booking_service.ExternalServices.BusProxy;
import com.bus.booking_service.ExternalServices.LoginProxy;
import com.bus.booking_service.ExternalServices.PaymentProxy;
import com.bus.booking_service.exception.BookingException;
import com.bus.booking_service.model.BookingModel;
import com.bus.booking_service.repository.BookingRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BusProxy busProxy;
	
	@Autowired
	private PaymentProxy paymentProxy;
	
	@Autowired
	private LoginProxy  loginProxy;
	
	@Override
	@CircuitBreaker(name = "bookBusCircuitBreaker",fallbackMethod = "bookBusFallBack")
	public BookingModel bookTicket(BookingModel booking) {
		String ticketNo=generateUniqueNo();
		booking.setTicketNo(ticketNo);
	    String busNo=booking.getBusNo();
	    BusModel busByNo=busProxy.getBusByNo(busNo);
	    
	    int numberOfTickets = booking.getNumberOfTickets();
		int seats = busByNo.getSeats();
		int reamingSets = seats - numberOfTickets;
		
		busByNo.setSeats(reamingSets);
		busProxy.updateBus(busNo, busByNo);
		int fare=busByNo.getFare();
		int totalCost=fare*numberOfTickets;
		
		booking.setCost(totalCost);
		
		BookingModel save=bookingRepository.save(booking);
		paymentProxy.doPayment(booking.getUsername(), ticketNo, totalCost);

		return save;
	}
	public String bookBusFallBack(BookingModel booking,Exception e) {
		return "Microservices are Down";
	}

	private String generateUniqueNo() {
		// Generate a random UUID and use it as a ticketNo number (you can customize this)
		Random r=new Random();
		long min = 1000000000L;
		long max = 9999999999L;
		long rno = min + ((long) (r.nextDouble() * (max - min)));
		String str = Long.toString(rno);
		return str;
		
	}

	@Override
	public String cancelTicket(String ticketNo) {
		BookingModel booking=bookingRepository.findByTicketNo(ticketNo);
		if (booking != null) {
			
			bookingRepository.deleteByTicketNo(ticketNo);

			String busNo = booking.getBusNo();
			BusModel busByNo = busProxy.getBusByNo(busNo);

			int numberOfTickets = booking.getNumberOfTickets();
			int seats = busByNo.getSeats();
			int reamingSets = seats + numberOfTickets;

			busByNo.setSeats(reamingSets);

			busProxy.updateBus(busNo, busByNo);
			return "Successfully Cancel the Ticket";
		} else {
			throw new BookingException("ticketNo not found");
		}

	}

	@Override
	public List<BookingModel> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public BookingModel getBookingByticketNo(String ticketNo) {
		BookingModel booking = bookingRepository.findByTicketNo(ticketNo);
		if (booking != null) {
			return booking;
		} else {
			throw new BookingException("No booking was found with the ticketNo: " + ticketNo);
		}
	}

	@Override
	public BusBookingVo getTicketDetailsWithBusDetails(String ticketNo) {
		BookingModel booking = bookingRepository.findByTicketNo(ticketNo);

		BusBookingVo vo = new BusBookingVo();

		if (booking != null) {
			String busNo = booking.getBusNo();
			BusModel busByNo = busProxy.getBusByNo(busNo);
			
			String username = booking.getUsername();
			 LoginModel userDetails = loginProxy.getbyUserName(username);

			
			
			vo.setBusModel(busByNo);
			vo.setBookingModel(booking);
			vo.setLoginModel(userDetails);
			
			bookingRepository.save(booking);

		} 
		return vo;
	}



	@Override
	public List<BookingModel> getBookingByUsername(String username) {
		List<BookingModel> all = bookingRepository.findAll();
	    return all.stream()
	              .filter(b -> b.getUsername().equals(username))
	              .collect(Collectors.toList());
	}

}
