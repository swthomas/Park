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
	public Address createNewAddress(Address address) {
		Address newAddress = new Address();
		newAddress = address;
		em.persist(newAddress);
		em.flush();
		return newAddress;
	}
}
