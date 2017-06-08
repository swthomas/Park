package controllers;

import java.util.List;

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

	@RequestMapping(value="parkingspot", method= RequestMethod.GET)
	public List<ParkingSpot> index() {
		return parkingSpotDAO.index();
	}
	
	@RequestMapping(value="parkingspot/{id}", method= RequestMethod.GET)
	public ParkingSpot show(@PathVariable Integer id) {
		return parkingSpotDAO.show(id);
	}
	
	
	/* Example AJAX POST -- The address and the sensor are persisted with the parking spot so the information has to be sent as well.  
	 *                      "OCCUPIED" field is to be set to false when first created
	 * 
	 *    { 
  			"description": "Parking spot for your car at my house",
  			"rate": 2.99,
  			"parkingSpotAddress": {
    			"street": "111 1st st.",
    			"street2": null,
    			"postalCode": 809231,
    			"city": "Denver",
    			"state": "CO",
    			"latitude": 0,
    			"longitude": 0
  			},
  			"parkingSensor": {
    			"occupied": false,
    			"serialNumber": "111111111"
  			}
		  }
	 *  
	 *  */
	
	@RequestMapping(value="parkingspot/{listerId}", method=RequestMethod.POST)
	public ParkingSpot create(@PathVariable Integer listerId, @RequestBody String jsonSpot) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ParkingSpot mappedSensor = mapper.readValue(jsonSpot, ParkingSpot.class);
			return parkingSpotDAO.create(listerId, mappedSensor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="parkingspot/{id}", method=RequestMethod.PUT)
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
	
	@RequestMapping(value="parkingspot/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return parkingSpotDAO.destroy(id);
	}
}