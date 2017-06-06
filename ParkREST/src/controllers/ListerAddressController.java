package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ListerAddressDAO;
import entities.ListerAddress;

@RestController
public class ListerAddressController {

	@Autowired
	private ListerAddressDAO addressDAO;
	
	@RequestMapping(value = "listerAddress/{id}", method = RequestMethod.GET)
	public ListerAddress show(@PathVariable Integer id) {
		return addressDAO.show(id);
	}

	@RequestMapping(value = "Listeraddress/{listerId}", method = RequestMethod.POST)
	public ListerAddress createListerAddress(@PathVariable Integer listerId, @RequestBody String jsonAddress) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ListerAddress mappedAddress = mapper.readValue(jsonAddress, ListerAddress.class);
			return addressDAO.createListerAddress(listerId, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "listerAddress/{listerId}", method = RequestMethod.PUT)
	public ListerAddress update(@PathVariable Integer id, @RequestBody String jsonAddress) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ListerAddress mappedAddress = mapper.readValue(jsonAddress, ListerAddress.class);
			return addressDAO.update(id, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}