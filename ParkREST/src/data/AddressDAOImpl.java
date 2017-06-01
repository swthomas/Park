package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Address;
import entities.Lister;
import entities.ParkingSpot;

@Transactional
@Repository
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Address show(Integer id) {
		return em.find(Address.class, id);
	}
	
	@Override
	public Address createListerAddress(Integer listerId, Address a) {
		Lister l = em.find(Lister.class, listerId);
		a.setParkingSpot(null);
		em.persist(a);
		em.flush();
		l.setAddress(a);
		return a;
	}
	
//	public Address createParkingSpotAddress(Integer parkingSpotId, Address a) {
//		List<ParkingSpot> parkingSpots = new ArrayList<>();
//		a.setParkingSpot(parkingSpots);
//		
//		em.persist(a);
//		em.flush();
//		
//		ParkingSpot p = em.find(ParkingSpot.class, parkingSpotId);
//		p.setAddress(a);
//		return a;
//	}

	@Override
	public Address update(Integer id, Address a) {
		Address address = em.find(Address.class, id);
		address.setStreet(a.getStreet());
		address.setStreet2(a.getStreet2());
		address.setCity(a.getCity());
		address.setState(a.getState());
		address.setPostalCode(a.getPostalCode());
		address.setLatitude(a.getLatitude());
		address.setLongitude(a.getLongitude());
		return address;
	}
	
	@Override
	public Boolean destroy(Integer id) {
		em.remove(em.find(Address.class, id));
		if (em.find(Address.class, id) == null) {
			return true;
		}
		return false;
	}
}