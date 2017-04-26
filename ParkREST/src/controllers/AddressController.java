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
	
	@RequestMapping(value = "addresses/{id}", method = RequestMethod.GET)
	public Address show(@PathVariable Integer id) {
		return addressDAO.show(id);
	}

	@RequestMapping(value = "addresses/{userId}", method = RequestMethod.POST)
	public Address create(@PathVariable Integer userId, @RequestBody String jsonAddress) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Address mappedAddress = mapper.readValue(jsonAddress, Address.class);
			return addressDAO.create(userId, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "addresses/{id}", method = RequestMethod.PUT)
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

	@RequestMapping(value="addresses/{id}", method= RequestMethod.DELETE)
		public Boolean destroy(@PathVariable Integer id) {
			return addressDAO.destroy(id);
		}	
}