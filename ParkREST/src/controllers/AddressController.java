package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.AddressDAO;
import entities.Address;

@RestController
public class AddressController {

	@Autowired
	private AddressDAO addressDAO;
	
	@RequestMapping(value = "address/{id}", method = RequestMethod.GET)
	public Address show(@PathVariable Integer id) {
		return addressDAO.show(id);
	}

	@RequestMapping(value = "addressLister/{listerId}", method = RequestMethod.POST)
	public Address createListerAddress(@PathVariable Integer listerId, @RequestBody String jsonAddress) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return addressDAO.createListerAddress(listerId, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	@RequestMapping(value = "addressParkingSpot/{parkingSpotId}", method = RequestMethod.POST)
//	public Address createParkingSpotAddress(@PathVariable Integer parkingSpotId, @RequestBody String jsonAddress) {
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
//			return addressDAO.createParkingSpotAddress(parkingSpotId, mappedAddress);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	@RequestMapping(value = "address/{id}", method = RequestMethod.PUT)
	public Address update(@PathVariable Integer id, @RequestBody String jsonAddress) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return addressDAO.update(id, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}