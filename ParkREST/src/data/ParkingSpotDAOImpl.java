package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Lister;
import entities.ParkingSpot;

@Transactional
@Repository
public class ParkingSpotDAOImpl implements ParkingSpotDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ParkingSpot> index() { 
		String q = "SELECT p FROM ParkingSpot p";
		return em.createQuery(q, ParkingSpot.class).getResultList();
	}
	
	@Override
	public List<ParkingSpot> distance(Double lat, Double lng) {
		String q = "SELECT p FROM ParkingSpot p " 
				 + "JOIN FETCH p.parkingSpotAddress a "
//				 + "ON p.parkingSpotAddress.id = a.id  " 
				 + "WHERE 1 > ( SELECT (6371 * acos(cos(radians(:lat)) * cos(radians(p.parkingSpotAddress.latitude)) * cos(radians(p.parkingSpotAddress.longitude) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.parkingSpotAddress.latitude)))))";
		return em.createQuery(q, ParkingSpot.class).setParameter("lat", lat).setParameter("lng", lng).getResultList();
	}
	
	
//	public List<ParkingSpot> initialLoad() {
//		String q = "SELECT p FROM ParkingSpot p WHERE NOT EXISTS (SELECT * FROM Reservation r WHERE r.parkingSpotId = :p.id BETWEEN r.reservedFromDate AND reservedToDate)";
//		return em.createQuery(q, ParkingSpot.class).getResultList();
//	}
	
	
	
	@Override
	public ParkingSpot show(Integer id) { 
		return em.find(ParkingSpot.class, id);
	}

	@Override
	public ParkingSpot create(Integer listerId, ParkingSpot p) {
		p.setLister(em.find(Lister.class, listerId));
		em.persist(p);
		em.flush();
		
		return p;
	}

	@Override
	public ParkingSpot update(Integer id, ParkingSpot spot) {
		ParkingSpot p = em.find(ParkingSpot.class, id);
		p.setDescription(spot.getDescription());
		p.setRate(spot.getRate());
		return p;
	}

	@Override
	public Boolean destroy(Integer id) {
		ParkingSpot p = em.find(ParkingSpot.class, id);
		em.remove(p);
		
		if(em.find(ParkingSpot.class, id) == null) {
			return true;
		}
		return false;
	}

}
