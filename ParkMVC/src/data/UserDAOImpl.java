package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CreditCard;
import entities.Lister;
import entities.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> index() {
		String q = "SELECT u FROM User u";
		return em.createQuery(q, User.class).getResultList();
	}
	
	@Override
	public User show(Integer id) {
		return em.find(User.class, id);
	}
	
	@Override
	public User show(String username, String password) {
		String query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
		return em.createQuery(query, User.class).setParameter("username", username).setParameter("password", password).getSingleResult();
	}
	
	@Override
	public User create(User u) {
		em.persist(u);
		em.flush();
		return u;
	}
	
	@Override
	public User update(User u) {
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public Boolean destroy(Integer id) {
		em.remove(em.find(User.class, id));
		if (em.find(CreditCard.class, id) == null) {
			return true;
		}
		return false;
	}
	
	// creditCards
	@Override
	public List<CreditCard> creditCardsIndex(Integer userId) {
		String query = "SELECT c FROM CreditCard c WHERE c.user.id = :userId";
		return em.createQuery(query, CreditCard.class).setParameter("userId", userId).getResultList();
	}
	
	// listers
	
	@Override
	public Lister listersIndex(Integer userId) {
		String query = "SELECT l FROM Lister l WHERE l.user.id = :userId";
		return em.createQuery(query, Lister.class).setParameter("userId", userId).getSingleResult();
	}
}
