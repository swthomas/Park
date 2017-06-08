package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Lister;
import entities.ListerAddress;
import entities.ParkingSpot;
import entities.User;

@Transactional
@Repository
public class ListerDAOImpl implements ListerDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Lister show(Integer id) {
		return em.find(Lister.class, id);
	}

	@Override
	public Lister create(Integer userId, Lister lister) {
		List<ParkingSpot> parkingSpots = new ArrayList<>();

		lister.setUser(em.find(User.class, userId));
		lister.setParkingSpots(parkingSpots);
		
		em.persist(lister);
		em.flush();
		return lister;
	}

	@Override
	public Lister update(Integer id, Lister lister) {
		Lister l = em.find(Lister.class, id);
		ListerAddress a = em.find(ListerAddress.class, l.getAddress().getId());
		
		l.setParkingSpots(lister.getParkingSpots());
		l.setPayPalAccount(lister.getPayPalAccount());
		
		a.setStreet(l.getAddress().getStreet());
		a.setStreet2(l.getAddress().getStreet2());
		a.setPostalCode(l.getAddress().getPostalCode());
		a.setCity(l.getAddress().getCity());
		a.setState(l.getAddress().getState());
		a.setLatitude(l.getAddress().getLatitude());
		a.setLongitude(l.getAddress().getLongitude());
		
		return l;
	}

	@Override
	public Boolean destroy(Integer id) {
		Lister l = em.find(Lister.class, id);
		em.remove(l);
		if (em.find(Lister.class, id) == null) {
			return true;
		}
		return false;
	}

}
