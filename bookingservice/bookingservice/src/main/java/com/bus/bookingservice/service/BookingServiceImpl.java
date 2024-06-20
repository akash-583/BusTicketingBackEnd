package com.bus.bookingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.bookingservice.ExternalClasses.BusBookingVo;
import com.bus.bookingservice.ExternalClasses.BusModel;
import com.bus.bookingservice.ExternalClasses.LoginModel;
import com.bus.bookingservice.ExternalServices.BusProxy;
import com.bus.bookingservice.ExternalServices.LoginProxy;
import com.bus.bookingservice.ExternalServices.PaymentProxy;
import com.bus.bookingservice.exception.BookingException;
import com.bus.bookingservice.model.BookingModel;
import com.bus.bookingservice.repository.BookingRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BookingServiceImpl implements BookingService {

	
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
//		BookingModel booking = bookingRepository.findByTicketNo(ticketNo);
//		
		Optional<BookingModel>findById=bookingRepository.findById(ticketNo);
		BookingModel booking=findById.get();
		if (booking != null) {
			
						bookingRepository.deleteById(ticketNo);
			
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
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

	@Override
	public BookingModel getBookingByTicketNo(String ticketNo) {
		BookingModel booking = bookingRepository.findByTicketNo(ticketNo);
		if (booking != null) {
			return booking;
		} else {
			throw new BookingException("No booking was found with the ticketNo: " + ticketNo);
		}
	}

	@Override
	public List<BookingModel> getBookingByUsername(String username) {
		 List<BookingModel> list = new ArrayList<BookingModel>();
	        List<BookingModel> all = bookingRepository.findAll();
//	        BookingModel findByUsername = bookingRepository.findByUsername(username);
	        for(BookingModel b : all) {
	        	
	         String username2 = b.getUsername();
	         if(username2.equals(username)) {
	        	 list.add(b);
	         }
	        }
	        
	        return list;
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
	}


