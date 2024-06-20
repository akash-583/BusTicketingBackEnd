package com.bus.bookingservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.bus.bookingservice.ExternalClasses.BusModel;
import com.bus.bookingservice.ExternalServices.BusProxy;
import com.bus.bookingservice.ExternalServices.LoginProxy;
import com.bus.bookingservice.ExternalServices.PaymentProxy;
import com.bus.bookingservice.model.BookingModel;
import com.bus.bookingservice.repository.BookingRepository;
import com.bus.bookingservice.service.BookingServiceImpl;

@SpringBootTest
class BookingserviceApplicationTests {

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
	    public void testCancelTicket() {
	        // Mock data
	        String ticketNo = "ticket123";
	        BookingModel booking = new BookingModel();
	        booking.setTicketNo(ticketNo);
	        booking.setBusNo("12345");
	        booking.setNumberOfTickets(2);

	        when(bookingRepository.findByTicketNo(ticketNo)).thenReturn(booking);
	        when(bookingRepository.deleteByTicketNo(ticketNo)).thenReturn("Successfully Cancel the Ticket");

	        when(busProxy.getBusByNo("ticket123")).thenReturn(new BusModel("ticket123","City A", "City B", 100, 50, "10:00 AM","10:00 PM"));

	        String result = bookingService.cancelTicket(ticketNo);

	        assertEquals("Successfully Cancel the Ticket", result);
	    }

}
