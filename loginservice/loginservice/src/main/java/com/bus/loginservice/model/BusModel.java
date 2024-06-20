package com.bus.loginservice.model;


import lombok.Data;


@Data

public class BusModel {
	
    private String busNo;
    
    private String source;

    private String destination;

    private int fare;

    private int seats;

    private String dropTime;
    
    private String boardingTime;
    
}
