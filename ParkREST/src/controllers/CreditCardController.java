package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.CreditCardDAO;
import entities.CreditCard;

@RestController
public class CreditCardController {

	@Autowired
	private CreditCardDAO creditCardDAO;
	
	@RequestMapping(value="creditcard/{id}", method= RequestMethod.GET)
	public CreditCard show(@PathVariable Integer id) {
		return creditCardDAO.show(id);
	}
	
	@RequestMapping(value="creditcard/{userId}", method=RequestMethod.POST)
	public CreditCard create(@PathVariable Integer userId, @RequestBody String jsonCard) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CreditCard mappedCreditCard = mapper.readValue(jsonCard, CreditCard.class);
			return creditCardDAO.create(userId, mappedCreditCard);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="creditcard/{id}", method=RequestMethod.PUT)
	public CreditCard update(@PathVariable Integer id, @RequestBody String jsonCard) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CreditCard mappedCreditCard = mapper.readValue(jsonCard, CreditCard.class);
			return creditCardDAO.update(id, mappedCreditCard);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="creditcard/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return creditCardDAO.destroy(id);
	}

}

