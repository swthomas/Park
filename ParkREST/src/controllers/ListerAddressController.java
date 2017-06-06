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
	
	@RequestMapping(value = "listerAddress/{listerId}", method = RequestMethod.GET)
	public ListerAddress show(@PathVariable Integer listerId) {
		return addressDAO.show(listerId);
	}

	@RequestMapping(value = "listerAddress/{listerId}", method = RequestMethod.PUT)
	public ListerAddress update(@PathVariable Integer listerId, @RequestBody String jsonAddress) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ListerAddress mappedAddress = mapper.readValue(jsonAddress, ListerAddress.class);
			return addressDAO.update(listerId, mappedAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}