package com.api.parkingcontrol.converter;

import com.api.parkingcontrol.dto.ParkingSpotDto;
import com.api.parkingcontrol.model.ParkingSpotModel;

public class Converter {
	
	
	public ParkingSpotModel convertToModel(ParkingSpotDto dto, ParkingSpotModel model) {
		
		model.setApartment(dto.getApartment());
		model.setCarBrand(dto.getCarBrand());
		model.setCarColor(dto.getCarColor());
		model.setCarPlate(dto.getCarPlate());
		model.setParkingSpotNumber(dto.getParkingSpotNumber());
		model.setOwnerName(dto.getOwnerName());
		
		return model;		
	}
	
	public ParkingSpotDto convertToDto(ParkingSpotModel model, ParkingSpotDto dto) {
		
		dto.setApartment(model.getApartment());
		dto.setCarBrand(model.getCarBrand());
		dto.setCarColor(model.getCarColor());
		dto.setCarPlate(model.getCarPlate());
		dto.setParkingSpotNumber(model.getParkingSpotNumber());
		dto.setOwnerName(model.getOwnerName());
		
		return dto;		
	}

}
