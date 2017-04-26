package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.User;
import entities.UserPayment;

@Transactional
@Repository
public class UserPaymentDAOImpl implements UserPaymentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserPayment show(Integer id) {
		return em.find(UserPayment.class, id);
	}

	@Override
	public UserPayment create(Integer userId, UserPayment u) {
		u.setUser(em.find(User.class, userId));
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public UserPayment update(Integer id, UserPayment u) {
		UserPayment payment = em.find(UserPayment.class, id);

		return payment;
	}
	
	@Override
	public Boolean destroy(Integer id) {
		em.remove(em.find(UserPayment.class, id));
		if (em.find(UserPayment.class, id) == null) {
			return true;
		}
		return false;
	}
}