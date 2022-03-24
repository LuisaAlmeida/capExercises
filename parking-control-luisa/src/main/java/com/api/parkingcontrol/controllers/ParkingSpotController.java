package com.api.parkingcontrol.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.parkingcontrol.converter.Converter;
import com.api.parkingcontrol.dto.ParkingSpotDto;
import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.returns.Returns;
import com.api.parkingcontrol.service.ParkingSpotService;

@RestController
@RequestMapping("/parking-spot")
public class ParkingSpotController {

	@Autowired
	private final ParkingSpotService service;


	public ParkingSpotController(ParkingSpotService service) {
		this.service = service;
	}


	@PostMapping("/getAll")
	public List<ParkingSpotModel> getAllParkingSpots(){
		return service.findAll();
	}


	@PostMapping
	@RequestMapping("/saveParkingSpot")
	public Returns saveParkingSpot(@RequestBody @Valid ParkingSpotDto dto) {
		ParkingSpotModel model = new ParkingSpotModel();
		Returns retValues = new Returns();
		try {
			model.setApartment(dto.getApartment());
			model.setCarBrand(dto.getCarBrand());
			model.setCarColor(dto.getCarColor());
			model.setCarPlate(dto.getCarPlate());
			model.setParkingSpotNumber(dto.getParkingSpotNumber());
			model.setOwnerName(dto.getOwnerName());
			service.save(model);

			retValues.setResult("OK");
			retValues.setMsg("ParkingSpot saved succesfully");
			retValues.setCat("201");

		} catch (Exception e) {

			retValues.setResult("NOK");
			retValues.setMsg("Error : " + e.getMessage());
			retValues.setCat("BAD STUFF");
		}
		return retValues;
	}


	@PostMapping("/getOneParking") //TODO simplificar
	public ResponseEntity<ParkingSpotDto> getOneParkingSpot(@RequestBody ParkingSpotDto dto){

		try {
			Converter converter = new Converter();
			Integer id = dto.getId();
			Optional<ParkingSpotModel> mOptional = service.findById(id);

			if (mOptional.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			ParkingSpotModel model = mOptional.get();
			dto = converter.convertToDto(model, dto);

			return new ResponseEntity<>(dto, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Integer id) {
		Optional<ParkingSpotModel> modelOptional = service.findById(id);
		if (!modelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
		}
		service.delete(modelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted sucessfully!");
	}


	@PutMapping("/{id}")
	public Returns updateParkingSpot(@PathVariable(value = "id") Integer id, @RequestBody @Valid ParkingSpotDto dto) {
		Optional<ParkingSpotModel> modelOptional = service.findById(id);
		Returns retValues = new Returns();
		if (!modelOptional.isPresent()) {
			retValues.setMsg("ERROR");
			retValues.setResult("400");
		}

		ParkingSpotModel model = modelOptional.get();
		model.setParkingSpotNumber(dto.getParkingSpotNumber());
		model.setCarPlate(dto.getCarPlate());
		model.setCarBrand(dto.getCarBrand());
		model.setCarColor(dto.getCarColor());
		model.setApartment(dto.getApartment());
		model.setOwnerName(dto.getOwnerName());

		service.save(model);

		retValues.setMsg("User successfully updated!");
		retValues.setCat("200");
		retValues.setResult("Borat says great success!");
		return retValues;
	}
}




