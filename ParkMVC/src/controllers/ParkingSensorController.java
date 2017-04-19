package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ParkingSensorDAO;
import entities.ParkingSensor;

@RestController
public class ParkingSensorController {

	@Autowired
	private ParkingSensorDAO sensorDAO;

	@RequestMapping(value="parkingsensors/{id}", method= RequestMethod.GET)
	public ParkingSensor show(@PathVariable Integer id) {
		return sensorDAO.show(id);
	}
	
	@RequestMapping(value="parkingsensors/{parkingSpotID}", method=RequestMethod.POST)
	public ParkingSensor create(@PathVariable Integer parkingSpotId, @RequestBody String jsonSensor) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ParkingSensor mappedSensor = mapper.readValue(jsonSensor, ParkingSensor.class);
			return sensorDAO.create(parkingSpotId, mappedSensor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="parkingsensors/{sensorId}", method=RequestMethod.PUT)
	public ParkingSensor update(@PathVariable Integer sensorId, @RequestBody String jsonSensor) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ParkingSensor mappedSensor = mapper.readValue(jsonSensor, ParkingSensor.class);
			return sensorDAO.update(sensorId, mappedSensor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="parkingsensors/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return sensorDAO.destroy(id);
	}
}
