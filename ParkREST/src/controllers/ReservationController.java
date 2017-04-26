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

	@RequestMapping(value="reservations/{id}", method= RequestMethod.GET)
	public Reservation show(@PathVariable Integer id) {
		return reservationDAO.show(id);
	}
	
	@RequestMapping(value="reservations/{uId}/{pId}/{cId}", method=RequestMethod.POST)
	public Reservation create(@RequestBody String jsonReservation, @PathVariable Integer uId, @PathVariable Integer pId, @PathVariable Integer cId) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Reservation mappedReservation = mapper.readValue(jsonReservation, Reservation.class);
			return reservationDAO.create(mappedReservation, uId, pId, cId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="reservations/{id}", method=RequestMethod.PUT)
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
	
	@RequestMapping(value="reservations/{id}", method= RequestMethod.DELETE)
	public Boolean destroy(@PathVariable Integer id) {
		return reservationDAO.destroy(id);
	}
}