package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.VehicleDAO;
import entities.Vehicle;

@RestController
public class VehicleController {

	@Autowired
	private VehicleDAO vehicleDAO;
	
	@RequestMapping(value = "vehicle/{id}", method = RequestMethod.GET)
	public Vehicle show(@PathVariable Integer id) {
		return vehicleDAO.show(id);
	}
	
	/*   AJAX POST request -- licensePlate is unique
	 * 
	 *   {
  			"year": 1981,
  			"color": "Red",
  			"make": "Porsche",
  			"model": "911 Targa",
  			"licensePlate": "test181"
		  }
	 */
	@RequestMapping(value="vehicle/{userId}", method=RequestMethod.POST)
	public Vehicle create(@PathVariable Integer userId, @RequestBody String jsonVehicle) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Vehicle mappedUser = mapper.readValue(jsonVehicle, Vehicle.class);
			return vehicleDAO.create(mappedUser, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="vehicle/{id}", method=RequestMethod.PUT)
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
	
	@RequestMapping(value="vehicle/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return vehicleDAO.destroy(id);
	}
	
}

