package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.UserDAO;
import entities.Lister;
import entities.Reservation;
import entities.User;
import entities.UserPayment;
import entities.Vehicle;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="user", method= RequestMethod.GET)
	public List<User> index() {
		return userDAO.index();
	}

	@RequestMapping(value="user/{id}", method= RequestMethod.GET)
	public User show(@PathVariable Integer id) {
		return userDAO.show(id);
	} 
	
	/*	AJAX POST request -- isLister to be set to false initially
	 *                     - phone number, email and username have to be unique
	 * 
	 * {
  			"firstName": "Steve",
  			"lastName": "Thompson",
  			"phoneNumber": 11122883333,
  			"email": "steveThomjhgpson@sd.com",
  			"username": "sthompskjhon",
  			"password": "password",
  			"isLister": false
		}
	 * 
	*/
	
	@RequestMapping(value="user", method=RequestMethod.POST)
	public User create(@RequestBody String jsonUser) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User mappedUser = mapper.readValue(jsonUser, User.class);
			return userDAO.create(mappedUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="user/{id}", method=RequestMethod.PUT)
	public User update(@PathVariable Integer id, @RequestBody String jsonUser) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User mappedUser = mapper.readValue(jsonUser, User.class);
			return userDAO.update(id, mappedUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="user/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return userDAO.destroy(id);
	}
	
	
	
	// listers methods
	@RequestMapping(value="user/{userId}/listers", method= RequestMethod.GET)
	public Lister listersIndex(@PathVariable Integer userId) {
		return userDAO.listersIndex(userId);
	}
	
	// listers methods
	@RequestMapping(value="user/{userId}/vehicles", method= RequestMethod.GET)
	public List<Vehicle> vehiclesIndex(@PathVariable Integer userId) {
		return userDAO.vehiclesIndex(userId);
	}
	
	// reservations methods
	@RequestMapping(value="user/{userId}/reservations", method= RequestMethod.GET)
	public List<Reservation> reservationsIndex(@PathVariable Integer userId) {
		return userDAO.reservationsIndex(userId);
	}
	
	// userPayments methods
	@RequestMapping(value="user/{userId}/userpayments", method= RequestMethod.GET)
	public List<UserPayment> userPaymentsIndex(@PathVariable Integer userId) {
		return userDAO.userPaymentsIndex(userId);
	}
	
}
