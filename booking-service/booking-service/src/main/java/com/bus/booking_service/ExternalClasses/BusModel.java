package com.bus.booking_service.ExternalClasses;

import org.springframework.data.annotation.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusModel {
	@Id
    private String busNo;

    private String source;

    private String destination;

 
    private int fare;

    private int seats;

    private String dropTime;
    
    private String boardingTime;
    
}
