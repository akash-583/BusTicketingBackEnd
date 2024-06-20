package com.bus.busservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.busservice.exception.BusException;
import com.bus.busservice.model.BusModel;
import com.bus.busservice.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService{

	
	@Autowired
	private BusRepository busRepository;
	
	@Override
	public BusModel addBusModel(BusModel bus) throws BusException {
		if (bus.getBusNo().isEmpty() ||
		        bus.getDropTime().isEmpty() ||
		        bus.getSource().isEmpty() ||
		        bus.getDestination().isEmpty() ||
		        bus.getBoardingTime().isEmpty() ||
		        bus.getSeats() == 0 ||
		        bus.getFare() == 0) {
		        throw new BusException("Please fill every field appropriately");
		   }
		    else {
		    return busRepository.save(bus);
		    }
	}

	@Override
	public List<BusModel> getAllBuses() throws BusException {
		List<BusModel> findAll = busRepository.findAll();
		if(!findAll.isEmpty()) {
			return findAll;
		}
		else {
			throw new BusException("No data is found");
		}
	}

	@Override
	public BusModel getBusById(String busNo) throws BusException {
		   Optional<BusModel> optionalBusModel = busRepository.findById(busNo);

		    if (optionalBusModel.isPresent()) {
		        return optionalBusModel.get();
		    } else {
		        throw new BusException("Bus with ID " + busNo + " not found");
		    }
	}

	@Override
	public String deleteBus(String busNo) throws BusException {
Optional<BusModel> optionalBusModel = busRepository.findById(busNo);
		
		if (optionalBusModel.isPresent()) {
		busRepository.deleteById(busNo);
		return "Bus is deleted Successfully";
		}
		else {
			throw new BusException("Bus is not exists with the Bus Id"+busNo);
		}
	}

	@Override
	public List<BusModel> findByLocation(String source, String destination) throws BusException {
		List<BusModel> busModels = busRepository.findBySourceAndDestination(source, destination);
		if(busModels.isEmpty()) {
			throw new BusException("No data is found for these destination");
		}
		else {
			return busModels;
		}
	}

	@Override
	public BusModel updateBus(String busNo, BusModel busModel) throws BusException {
		Optional<BusModel> findById = busRepository.findById(busNo);
		if(findById.isPresent()) {
			 BusModel bus = findById.get();
			bus.setSource(busModel.getSource());
			 bus.setDestination(busModel.getDestination());
			 bus.setSeats(busModel.getSeats());
			 bus.setFare(busModel.getFare());
			 bus.setBoardingTime(busModel.getBoardingTime());
			 bus.setDropTime(busModel.getDropTime());
			 BusModel updated = busRepository.save(bus);
			return updated;
		}
		else {
			throw new BusException("It doesn't exists for modification");
		}
	}



}
