package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Lister;
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
		Lister l = lister;
		l.setUser(em.find(User.class, userId));
		em.persist(lister);
		em.flush();
		return lister;
	}

	@Override
	public Lister update(Integer id, Lister lister) {
		Lister l = em.find(Lister.class, id);
		l.setParkingSpot(lister.getParkingSpot());
		l.setPayPalAccount(lister.getPayPalAccount());
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
