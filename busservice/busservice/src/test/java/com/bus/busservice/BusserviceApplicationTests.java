package com.bus.busservice;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bus.busservice.exception.BusException;
import com.bus.busservice.model.BusModel;
import com.bus.busservice.repository.BusRepository;
import com.bus.busservice.service.BusService;

@SpringBootTest
class BusserviceApplicationTests {

	@Autowired
	private BusService busServiceImpl;
	
	@MockBean
	private BusRepository busRepository;
	
	@Test
	public void addBusModel_test() {
		BusModel Bus = new BusModel("1","Hyderabad", "Chennai",1500,45, "10:00 PM","8:00 AM");
		when(busRepository.save(Bus)).thenReturn(Bus);
		assertEquals(Bus, busServiceImpl.addBusModel(Bus));
	}

	@Test
	public void addBusModel_MissingFields() {
		BusModel Bus = new BusModel("","Hyderabad", "Chennai",1500,45, "10:00 PM","8:00 AM");
		assertThrows(BusException.class, () -> {
			busServiceImpl.addBusModel(Bus);
		});
	}
	@Test
	public void getBuss_DataFound() {
		List<BusModel> bus = new ArrayList<>();


		bus.add(new BusModel("1","Hyderabad", "Chennai", 1500, 45, "10:00 PM","8:00 AM"));
		bus.add(new BusModel("2","Bangalore", "Mysore", 1550, 45, "10:00 PM","8:00 AM"));

		when(busRepository.findAll()).thenReturn(bus);
		assertEquals(bus.size(), busServiceImpl.getAllBuses().size());
	}
	
	
	@Test
	public void getBusByBusNo_DataFound() {
		BusModel Bus = new BusModel("1","Hyderabad", "Chennai", 1500, 45, "10:00 PM","8:00 AM");
		when(busRepository.findById("1")).thenReturn(Optional.of(Bus));
		Optional<BusModel> result = Optional.of(busServiceImpl.getBusById("1"));
		assertEquals(Bus, result.get());
	}
	
	@Test
	public void getBusByBusNo_DataNotFound() {
	    when(busRepository.findById("NotFound")).thenReturn(Optional.empty());
	    
	    assertThrows(BusException.class, () -> {
	        busServiceImpl.getBusById("Bus NotFound");
	    });
	}
	
	
	@Test
	public void findByLocation_DataFound() {
		BusModel train = new BusModel("1","Hyderabad", "Chennai",1500, 67, "10:00 PM","8:00 AM");
		List<BusModel> expectedResults = List.of(train);
		when(busRepository.findBySourceAndDestination("Hyderabad", "Chennai")).thenReturn(expectedResults);
		List<BusModel> result = busServiceImpl.findByLocation("Hyderabad", "Chennai");
		assertEquals(expectedResults, result);
	}
	
	@Test
	public void findByLocation_DataNotFound() {
	  
	    when(busRepository.findBySourceAndDestination("aaa", "bbb")).thenReturn(new ArrayList<>());

	    assertThrows(BusException.class, () -> {
	        busServiceImpl.findByLocation("aaa", "bbb");
	    });
	}
	
	@Test
	public void deleteBus_Exists() {
		BusModel train = new BusModel("1","Hyderabad", "Chennai",1500, 67, "10:00 PM","8:00 AM");
	    when(busRepository.findById("1")).thenReturn(Optional.of(train));
	    String result = busServiceImpl.deleteBus("1");
	    assertEquals("Bus is deleted Successfully", result);
	}
	
	
	@Test
	public void deleteBus_NotExists() {
	    when(busRepository.findById("2")).thenReturn(Optional.empty());

	    assertThrows(BusException.class, () -> {
	        busServiceImpl.deleteBus("2");
	    });
	}
	
	
	
	
	
	
	
	
	
	
	
}
