package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Address;

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
	public Address create(Integer userId, Address a) {
		em.persist(a);
		em.flush();
		return a;
	}

	@Override
	public Address update(Integer id, Address a) {
		Address address = em.find(Address.class, id);
		address.setStreet(a.getStreet());
		address.setStreet2(a.getStreet2());
		address.setCity(a.getCity());
		address.setState(a.getState());
		address.setPostalCode(a.getPostalCode());
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