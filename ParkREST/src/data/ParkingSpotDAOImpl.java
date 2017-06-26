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
	
// 		https://stackoverflow.com/questions/8994718/mysql-longitude-and-latitude-query-for-other-rows-within-x-mile-radius
//		https://developers.google.com/maps/solutions/store-locator/clothing-store-locator#findnearsql	
	
//				SELECT
//			    `id`,
//			    (
//			        6371 *
//			        acos(
//			            cos( radians( :lat ) ) *
//			            cos( radians( `lat` ) ) *
//			            cos(
//			                radians( `long` ) - radians( :long )
//			            ) +
//			            sin(radians(:lat)) *
//			            sin(radians(`lat`))
//			        )
//			    ) `distance`
//			FROM
//			    `location`
//			HAVING
//			    `distance` < :distance
//			ORDER BY
//			    `distance`
//			LIMIT
//			    25
	
	
// 		https://gis.stackexchange.com/questions/31628/find-points-within-a-distance-using-mysql
	
//			SELECT
//			  id, (
//			    3959 * acos (
//			      cos ( radians(78.3232) )
//			      * cos( radians( lat ) )
//			      * cos( radians( lng ) - radians(65.3234) )
//			      + sin ( radians(78.3232) )
//			      * sin( radians( lat ) )
//			    )
//			  ) AS distance
//			FROM markers
//			HAVING distance < 30
//			ORDER BY distance
//			LIMIT 0 , 20;
	
	@Override
	public List<ParkingSpot> distance(Double lat, Double lng) {
		String q = "SELECT p FROM ParkingSpot p " 
				 + "JOIN FETCH ParkingSpotAddress a "
				 + "ON p.parkingSpotAddress.id = a.id  " 
				 + "WHERE 1.0 > :lat";
		return em.createQuery(q, ParkingSpot.class).setParameter("lat", lat).getResultList();
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
