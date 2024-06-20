package com.bus.busservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.busservice.exception.BusException;
import com.bus.busservice.model.BusModel;
import com.bus.busservice.service.BusService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
	BusService busService;


	@PostMapping("/addbus")
	public ResponseEntity<BusModel> addBusModel(@RequestBody @Valid BusModel bus){
		log.info("Inside the addBusModel method of Controller");
		log.info("Adding the Bus Details "+bus);
		return ResponseEntity.ok(busService.addBusModel(bus));
	}
	
	@GetMapping("/viewallbuses")
	public ResponseEntity<List<BusModel>> getAllBuses(){
		log.info("Inside the getAllBuses method of Controller");
		log.info("Retrieving Buses Data ");
		return ResponseEntity.ok(busService.getAllBuses());
	}

	@GetMapping("viewbusbybusNo/{busNo}")
	public ResponseEntity<BusModel> getBusById(@PathVariable String busNo){
		log.info("Inside the getBusById method of Controller");
		log.info("Retrieving Bus by BusNo");
		return ResponseEntity.ok(busService.getBusById(busNo));
	}

	@DeleteMapping("/deletebus/{busNo}")
	public ResponseEntity<String> deleteBus(@PathVariable String busNo){
		log.info("Inside the deleteBus method of Controller");
		log.info("Delete Bus by BusNo");
		return ResponseEntity.ok(busService.deleteBus(busNo));
	}

	@GetMapping("/findbetween/{source}/{destination}")
	public ResponseEntity<List<BusModel>> findByLocation(@PathVariable String source, @PathVariable String destination) throws BusException{
		log.info("Inside the findByLocation method of Controller");
		log.info("Retrieving Trains by Destination ");
		return ResponseEntity.ok(busService.findByLocation(source,destination));
	}
	
	@PutMapping("updatebusbyid/{busNo}")
	public ResponseEntity<BusModel> updateBus(@PathVariable String busNo,@RequestBody BusModel BusModel) throws BusException {
		log.info("Existing data has been updated");
		return ResponseEntity.ok(busService.updateBus(busNo, BusModel));
	}
}
