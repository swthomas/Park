package data;

import java.time.LocalDateTime;
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
		
		String haversine = "(6371 * acos(cos(radians(:lat)) "
				+ "* cos(radians(p.parkingSpotAddress.latitude)) "
				+ "* cos(radians(p.parkingSpotAddress.longitude) "
				+ "- radians(:lng)) + sin(radians(:lat)) "
				+ "* sin(radians(p.parkingSpotAddress.latitude))))";	
		
		String q = "SELECT p FROM ParkingSpot p " 
				 + "JOIN FETCH p.parkingSpotAddress a "
				 + "WHERE " + haversine + " < 1";
		
		return em.createQuery(q, ParkingSpot.class).setParameter("lat", lat).setParameter("lng", lng).getResultList();
	}	
	
	
	@Override
	public List<ParkingSpot> distanceTEST(Double lat, Double lng) {
				
		String haversine = "(6371 * acos(cos(radians(:lat)) "
							+ "* cos(radians(p.parkingSpotAddress.latitude)) "
							+ "* cos(radians(p.parkingSpotAddress.longitude) "
							+ "- radians(:lng)) + sin(radians(:lat)) "
							+ "* sin(radians(p.parkingSpotAddress.latitude))))";
		
		String q = "SELECT p FROM ParkingSpot p " 
				 + "JOIN FETCH p.parkingSpotAddress "
				 + "WHERE " + haversine + " < .01";
		
		return em.createQuery(q, ParkingSpot.class).setParameter("lat", lat).setParameter("lng", lng).getResultList();
	}	
	
	
	
// *** Search for parking spots without Reservation Between certain times ***	
// *** Will need to be added to above search once we figure some shit out ***	
	
	@Override
	public List<ParkingSpot> reservationTEST() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime currentDateTimeMinus2Hours = currentDateTime.minusHours(2);
		LocalDateTime currentDateTimePlus2Hours = currentDateTime.plusHours(2);
		
		System.out.println(currentDateTime + "****" + currentDateTimeMinus2Hours + "****" + currentDateTimePlus2Hours);
		
		
		String q = "SELECT p FROM ParkingSpot p "
				 + "WHERE NOT EXISTS (SELECT r FROM Reservation r "
				 + "WHERE r.parkingSpotId = p.id "
				 + "AND r.reservedFromDate BETWEEN :currentDateTimeMinus2Hours AND :currentDateTimePlus2Hours "
				 + "AND r.reservedToDate BETWEEN :currentDateTime AND :currentDateTimePlus2Hours)";
		
		return em.createQuery(q, ParkingSpot.class).setParameter("currentDateTime", currentDateTime).setParameter("currentDateTimePlus2Hours", currentDateTimePlus2Hours).setParameter("currentDateTimeMinus2Hours", currentDateTimeMinus2Hours).getResultList();
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
