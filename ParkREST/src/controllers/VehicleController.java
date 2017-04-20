package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.VehicleDAO;
import entities.User;
import entities.Vehicle;

@RestController
public class VehicleController {

	@Autowired
	private VehicleDAO vehicleDAO;
	
	@RequestMapping(value="vehicles", method=RequestMethod.POST)
	public Vehicle create(@RequestBody String jsonVehicle) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Vehicle mappedUser = mapper.readValue(jsonVehicle, Vehicle.class);
			return vehicleDAO.create(mappedUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="vehicles/{id}", method=RequestMethod.PUT)
	public Vehicle update(@RequestBody String jsonUser, @PathVariable int id) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Vehicle mappedVehicle = mapper.readValue(jsonUser, Vehicle.class);
			return vehicleDAO.update(id, mappedVehicle);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="users/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return vehicleDAO.destroy(id);
	}
	
}

