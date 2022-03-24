package com.api.parkingcontrol.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;

@Service
public class ParkingSpotService {
	
	
	@Autowired
	private ParkingSpotRepository repository;
	
	
	public ParkingSpotService(ParkingSpotRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ParkingSpotModel save(ParkingSpotModel model) {
		return repository.save(model);
	}

//	public boolean existsByCarPlate(String carPlate) {
//		return psRepository.existsByCarPlate(carPlate);
//	}
//
//	public boolean existsByParkingSpotNumber(String psNumber) {
//		return psRepository.existsByParkingSpotNumber(psNumber);
//	}
//
//	public boolean existsByApartment(String apartment) {
//		return psRepository.existsByApartment(apartment);
//	}
//	
//	public boolean existsById(Integer id) {
//		return psRepository.exitsById(id);
//	}

	public List<ParkingSpotModel> findAll() {
		return repository.findAll();
	}
	
//	public Optional<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber) {
//		return psRepository.findByParkingSpotNumber(parkingSpotNumber);
//	}
	
	@Transactional
	public void delete(ParkingSpotModel model) {
		repository.delete(model);
	}

	public Optional<ParkingSpotModel> findById(Integer id) {
		return repository.findById(id);
	}
	
}
