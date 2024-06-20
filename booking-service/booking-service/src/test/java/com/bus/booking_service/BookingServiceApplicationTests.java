package com.bus.booking_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bus.booking_service.ExternalClasses.BusModel;
import com.bus.booking_service.ExternalServices.BusProxy;
import com.bus.booking_service.ExternalServices.LoginProxy;
import com.bus.booking_service.ExternalServices.PaymentProxy;
import com.bus.booking_service.model.BookingModel;
import com.bus.booking_service.repository.BookingRepository;
import com.bus.booking_service.service.BookingServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



@SpringBootTest
class BookingServiceApplicationTests {

	 @InjectMocks
	    private BookingServiceImpl bookingService;

	    @Mock
	    private BookingRepository bookingRepository;

	    @Mock
	    private BusProxy busProxy;

	    @Mock
	    private LoginProxy loginProxy;
	    
	    @Mock
	    private PaymentProxy paymentProxy;
	    
	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
	    @Test

	    void testBookTicket() {
	        BookingModel booking = new BookingModel();
	        booking.setBusNo("123");
	        booking.setNumberOfTickets(2);
	        booking.setUsername("testUser");

	        BusModel train = new BusModel();
	        train.setBusNo("123");
	        train.setSeats(10);
	        train.setFare(50);

	        when(busProxy.getBusByNo("123")).thenReturn(train);   
	        BookingModel result = bookingService.bookTicket(booking);
	        verify(bookingRepository, times(1)).save(booking);
	        verify(paymentProxy, times(1)).doPayment("testUser", booking.getTicketNo(), 100); // Assuming fare is 50 and numberOfTickets is 2

	    }
	    
	    @Test
	    public void testCancelTicket() {
	        // Mock data
	        String ticketNo = "Ticket123";
	        BookingModel booking = new BookingModel();
	        booking.setTicketNo(ticketNo);
	        booking.setBusNo("12345");
	        booking.setNumberOfTickets(2);

	        when(bookingRepository.findByTicketNo(ticketNo)).thenReturn(booking);
	        when(bookingRepository.deleteByTicketNo(ticketNo)).thenReturn("Successfully Cancel the Ticket");

	        when(busProxy.getBusByNo("12345")).thenReturn(new BusModel("12345","City A", "City B", 1000, 50, "10:00 AM","8:00 PM"));

	        String result = bookingService.cancelTicket(ticketNo);

	        assertEquals("Successfully Cancel the Ticket", result);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
