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
import entities.CreditCard;
import entities.Lister;
import entities.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="users", method= RequestMethod.GET)
	public List<User> index() {
		return userDAO.index();
	}

	@RequestMapping(value="users/{id}", method= RequestMethod.GET)
	public User show(@PathVariable Integer id) {
		return userDAO.show(id);
	}
	
	@RequestMapping(value="users/{username}{password}", method= RequestMethod.GET)
	public User show(@PathVariable String username, @PathVariable String password) {
		return userDAO.show(username, password);
	}
	
	@RequestMapping(value="users", method=RequestMethod.POST)
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
	
	@RequestMapping(value="users", method=RequestMethod.PUT)
	public User update(@RequestBody String jsonUser) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User mappedUser = mapper.readValue(jsonUser, User.class);
			return userDAO.update(mappedUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="users/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return userDAO.destroy(id);
	}
	
	// creditCards methods
	
	@RequestMapping(value="users/{userId}/creditcards", method= RequestMethod.GET)
	public List<CreditCard> index(@PathVariable Integer userId) {
		return userDAO.creditCardsIndex(userId);
	}
	
	// listers methods
	
	@RequestMapping(value="users/{userId}/listers", method= RequestMethod.GET)
	public Lister listersIndex(@PathVariable Integer userId) {
		return userDAO.listersIndex(userId);
	}
	
}
