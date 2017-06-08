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
