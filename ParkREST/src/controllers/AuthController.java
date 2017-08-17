package controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.AuthDAO;
import entities.User;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthDAO authDAO;
	
	@RequestMapping(path="/registerUser", method=RequestMethod.POST)
	public User register(HttpSession session, @RequestBody User user) {
		User newUser = authDAO.register(user);
		session.setAttribute("sessionUser", newUser);
		return newUser;
	}
	
	@RequestMapping(path="/registerLister", method=RequestMethod.POST)
	public User registerLister(HttpSession session, @RequestBody User lister) {
		System.out.println("in register controller");
		User newLister = authDAO.register(lister);
		session.setAttribute("sessionUser", newLister);
		return newLister;
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public User login(HttpSession session, HttpServletResponse response, @RequestBody User user) {
		User sessionUser = authDAO.authenticateUser(user);
		if (sessionUser == null) {
			response.setStatus(401);
		} else {
		session.setAttribute("sessionUser", sessionUser);
		}
		return sessionUser;
	}
	
	@RequestMapping(path="/listerlogin", method=RequestMethod.POST)
	public User loginLister(HttpSession session, HttpServletResponse response, @RequestBody User lister) {
		User sessionUser = authDAO.authenticateUser(lister);
		if (sessionUser.getIsLister() == true) {
			session.setAttribute("sessionUser", sessionUser);
		} else {
			response.setStatus(401);
			sessionUser = null;
		}
		return sessionUser;
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public Boolean logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("sessionUser");
		return true;
	}
	
	@RequestMapping(path="/unauthorized")
	public String unauth(HttpServletResponse response) {
		response.setStatus(401);
		return "unauthorized";
	}

}
