package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ListerDAO;
import entities.Lister;

@RestController
public class ListerController {
	
	@Autowired
	private ListerDAO listerDAO;
	
	@RequestMapping(value="lister/{id}", method= RequestMethod.GET)
	public Lister show(@PathVariable Integer id) {
		return listerDAO.show(id);
	}

	@RequestMapping(value="lister/{userId}", method=RequestMethod.POST)
	public Lister create(@PathVariable Integer userId, @RequestBody String jsonLister) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Lister mappedLister = mapper.readValue(jsonLister, Lister.class);
			return listerDAO.create(userId, mappedLister);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="lister/{id}", method=RequestMethod.PUT)
	public Lister update(@PathVariable Integer id, @RequestBody String jsonLister) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Lister mappedLister = mapper.readValue(jsonLister, Lister.class);
			return listerDAO.update(id, mappedLister);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
