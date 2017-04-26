package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CreditCard;
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
	public Reservation create(Reservation r, Integer uId, Integer pId, Integer cId) {
		r.setUser(em.find(User.class, uId));
		r.setParkingSpot(em.find(ParkingSpot.class, pId));
		r.setCreditCard(em.find(CreditCard.class, cId));
		em.persist(r);
		em.flush();
		return r;
	}

	@Override
	public Reservation update(Integer id, Reservation a) {
		Reservation reservation = em.find(Reservation.class, id);
		reservation.setCreditCard(a.getCreditCard());
		reservation.setReservedFromDate(a.getReservedFromDate());
		reservation.setReservedToDate(a.getReservedToDate());
		reservation.setCreditCard(a.getCreditCard());
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