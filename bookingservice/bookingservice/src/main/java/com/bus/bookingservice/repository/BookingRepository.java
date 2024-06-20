package com.bus.bookingservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bus.bookingservice.model.BookingModel;

public interface BookingRepository extends MongoRepository<BookingModel,String>{

	BookingModel findByTicketNo(String ticketNo);

	String deleteByTicketNo(String ticketNo);

	BookingModel findByUsername(String username);
}
