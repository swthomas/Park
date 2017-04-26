package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ParkingSpotDAO;
import entities.ParkingSpot;

@RestController
public class ParkingSpotController {

	@Autowired
	private ParkingSpotDAO parkingSpotDAO;

	@RequestMapping(value="parkingspots/{id}", method= RequestMethod.GET)
	public ParkingSpot show(@PathVariable Integer id) {
		return parkingSpotDAO.show(id);
	}
	
	@RequestMapping(value="parkingspots/{listerId}/{addressId}", method=RequestMethod.POST)
	public ParkingSpot create(@PathVariable Integer listerId, @PathVariable Integer addressId, @RequestBody String jsonSpot) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ParkingSpot mappedSensor = mapper.readValue(jsonSpot, ParkingSpot.class);
			return parkingSpotDAO.create(listerId, addressId, mappedSensor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="parkingspots/{id}", method=RequestMethod.PUT)
	public ParkingSpot update(@PathVariable Integer id, @RequestBody String jsonSpot) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ParkingSpot mappedSensor = mapper.readValue(jsonSpot, ParkingSpot.class);
			return parkingSpotDAO.update(id, mappedSensor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="parkingspots/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return parkingSpotDAO.destroy(id);
	}
}