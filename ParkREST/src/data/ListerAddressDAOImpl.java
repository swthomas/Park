package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Lister;
import entities.ListerAddress;

@Transactional
@Repository
public class ListerAddressDAOImpl implements ListerAddressDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public ListerAddress show(Integer id) {
		return em.find(ListerAddress.class, id);
	}
	
	@Override
	public ListerAddress createListerAddress(Integer listerId, ListerAddress a) {
		Lister l = em.find(Lister.class, listerId);
		em.flush();
		l.setAddress(a);
		return a;
	}

	@Override
	public ListerAddress update(Integer id, ListerAddress a) {
		Lister l = em.find(Lister.class, id);
		ListerAddress address = em.find(ListerAddress.class, l.getAddress().getId());
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
		em.remove(em.find(ListerAddress.class, id));
		if (em.find(ListerAddress.class, id) == null) {
			return true;
		}
		return false;
	}
}