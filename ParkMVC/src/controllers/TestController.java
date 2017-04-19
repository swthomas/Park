package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(path = "ping", method = RequestMethod.GET)
	public String ping() {
		System.out.println("insides test controller ping");
	  return "pong";
	}	
}
