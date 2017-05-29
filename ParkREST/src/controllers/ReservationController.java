package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ReservationDAO;
import entities.Reservation;

@RestController
public class ReservationController {

	@Autowired
	private ReservationDAO reservationDAO;

	@RequestMapping(value="reservation/{id}", method= RequestMethod.GET)
	public Reservation show(@PathVariable Integer id) {
		return reservationDAO.show(id);
	}
	
	@RequestMapping(value="reservation/{userId}/{parkginSpotId}/{creditCardId}", method=RequestMethod.POST)
	public Reservation create(@RequestBody String jsonReservation, @PathVariable Integer userId, @PathVariable Integer parkingSpotId) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Reservation mappedReservation = mapper.readValue(jsonReservation, Reservation.class);
			return reservationDAO.create(mappedReservation, userId, parkingSpotId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="reservation/{id}", method=RequestMethod.PUT)
	public Reservation update(@PathVariable Integer id, @RequestBody String jsonReservation) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Reservation mappedReservation = mapper.readValue(jsonReservation, Reservation.class);
			return reservationDAO.update(id, mappedReservation);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="reservation/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return reservationDAO.destroy(id);
	}
}