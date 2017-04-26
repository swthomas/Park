package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.UserPaymentDAO;
import entities.UserPayment;

public class UserPaymentController {

	@Autowired
	private UserPaymentDAO userpaymentDAO;
	
	@RequestMapping(value = "userpayments/{id}", method = RequestMethod.GET)
	public UserPayment show(@PathVariable Integer id) {
		return userpaymentDAO.show(id);
	}
	
	@RequestMapping(value="userpayments/{userId}", method=RequestMethod.POST)
	public UserPayment create(@PathVariable Integer userId, @RequestBody String jsonPayment) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserPayment mappedPayment = mapper.readValue(jsonPayment, UserPayment.class);
			return userpaymentDAO.create(userId, mappedPayment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="userpayments/{id}", method=RequestMethod.PUT)
	public UserPayment update(@RequestBody String jsonPayment, @PathVariable int id) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserPayment mappedPayment = mapper.readValue(jsonPayment, UserPayment.class);
			return userpaymentDAO.update(id, mappedPayment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="userpayments/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return userpaymentDAO.destroy(id);
	}
	
}
