package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.ParkingSpot;
import entities.Reservation;
import entities.User;

@Transactional
@Repository
public class ReservationDAOImpl implements ReservationDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Reservation show(Integer id) {
		return em.find(Reservation.class, id);
	}

	@Override
	public Reservation create(Reservation r, Integer userId, Integer parkingSpotId) {
		r.setUser(em.find(User.class, userId));
		User u = em.find(User.class, userId);
		r.setParkingSpot(em.find(ParkingSpot.class, parkingSpotId));
		em.persist(r);
		em.flush();
		u.getReservations().add(r);
		return r;
	}

	@Override
	public Reservation update(Integer id, Reservation a) {
		Reservation reservation = em.find(Reservation.class, id);
		reservation.setReservedFromDate(a.getReservedFromDate());
		reservation.setReservedToDate(a.getReservedToDate());
		return reservation;
	}
	
	@Override
	public Boolean destroy(Integer id) {
		em.remove(em.find(Reservation.class, id));
		if (em.find(Reservation.class, id) == null) {
			return true;
		}
		return false;
	}

}