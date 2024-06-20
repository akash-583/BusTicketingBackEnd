package com.bus.loginservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bus.loginservice.model.BusModel;

import jakarta.validation.Valid;

@FeignClient(name = "BusService", url="http://localhost:9004/bus")
public interface BusProxy {
	@PostMapping("/addbus")
	public BusModel addBusModel(@RequestBody @Valid BusModel bus) ;
	
	@GetMapping("/viewallbuses")
	public List<BusModel> getAllBuses();

	@GetMapping("viewbusbybusNo/{busNo}")
	public BusModel getBusById(@PathVariable String busNo);
	@DeleteMapping("/deletebus/{busNo}")
	public String deleteBus(@PathVariable String busNo);

	@GetMapping("/findbetween/{source}/{destination}")
	public List<BusModel> findByLocation(@PathVariable String source, @PathVariable String destination) ;
	
	@PutMapping("updatebusbyid/{busNo}")
	public BusModel updateBus(@PathVariable String busNo,@RequestBody BusModel BusModel);
}
