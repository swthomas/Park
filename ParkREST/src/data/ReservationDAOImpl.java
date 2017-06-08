package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.ParkingSpot;
import entities.Reservation;
import entities.User;
import entities.Vehicle;

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
	public List<Reservation> index(Integer userId) {
		String q = "SELECT r FROM Reservation r WHERE r.user.id = :userId";
		return em.createQuery(q, Reservation.class).setParameter("userId", userId).getResultList();
	}
	
	@Override
	public Reservation create(Reservation r, Integer userId, Integer parkingSpotId, Integer vehicleId) {
		r.setUser(em.find(User.class, userId));
		r.setParkingSpot(em.find(ParkingSpot.class, parkingSpotId));
		r.setVehicle(em.find(Vehicle.class, vehicleId));
		em.persist(r);
		em.flush();
		return r;
	}

	@Override
	public Reservation update(Integer id, Reservation r) {
		Reservation reservation = em.find(Reservation.class, id);
		reservation.setReservedFromDate(r.getReservedFromDate());
		reservation.setReservedToDate(r.getReservedToDate());
		reservation.setVehicle(em.find(Vehicle.class, r.getVehicle().getId()));
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