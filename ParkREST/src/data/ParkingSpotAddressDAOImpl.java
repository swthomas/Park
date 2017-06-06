package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Lister;
import entities.ParkingSpot;
import entities.ParkingSpotAddress;

@Transactional
@Repository
public class ParkingSpotAddressDAOImpl implements ParkingSpotAddressDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public ParkingSpotAddress show(Integer id) {
		return em.find(ParkingSpotAddress.class, id);
	}
	
	@Override
	public ParkingSpotAddress create(Integer parkingSpotId, ParkingSpotAddress a) {
		ParkingSpot p = em.find(ParkingSpot.class, parkingSpotId);
		em.persist(a);
		em.flush();
		p.setAddress(a);
		return a;
	}

	@Override
	public ParkingSpotAddress update(Integer id, ParkingSpotAddress a) {
		Lister l = em.find(Lister.class, id);
		ParkingSpotAddress address = em.find(ParkingSpotAddress.class, l.getAddress().getId());
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
		em.remove(em.find(ParkingSpotAddress.class, id));
		if (em.find(ParkingSpotAddress.class, id) == null) {
			return true;
		}
		return false;
	}
}