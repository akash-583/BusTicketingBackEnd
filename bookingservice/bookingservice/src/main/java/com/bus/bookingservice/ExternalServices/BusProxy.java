package com.bus.bookingservice.ExternalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bus.bookingservice.ExternalClasses.BusModel;



@FeignClient(name = "BusService", url="http://localhost:9004/bus")
public interface BusProxy {
 	
	@GetMapping(value = "/viewbusbybusNo/{busNo}")
	public BusModel getBusByNo(@PathVariable String busNo);
	
	@PutMapping("updatebusbyid/{busNo}")
	public BusModel updateBus(@PathVariable String busNo,@RequestBody BusModel BusModel); 
	
	
}