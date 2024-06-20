package com.bus.booking_service.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bus.booking_service.model.BookingModel;
@Repository
public interface BookingRepository extends MongoRepository<BookingModel, String>{

	BookingModel findByTicketNo(String ticketNo);

	String deleteByTicketNo(String ticketNo);

	BookingModel findByUsername(String username);
}
