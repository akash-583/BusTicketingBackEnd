package com.bus.busservice.service;

import java.util.List;

import com.bus.busservice.exception.BusException;
import com.bus.busservice.model.BusModel;

public interface BusService {
	public BusModel addBusModel (BusModel bus) throws BusException;
	public List<BusModel> getAllBuses() throws BusException;
	public BusModel getBusById(String busNo) throws BusException;
	public String deleteBus(String busNo) throws BusException;
	public List<BusModel> findByLocation(String source,String destination) throws BusException;
	public BusModel updateBus(String busNo, BusModel BusModel) throws BusException;
}