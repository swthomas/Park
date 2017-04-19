package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CreditCard;

@Transactional
@Repository
public class CreditCardDAOImpl implements CreditCardDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public CreditCard show(Integer id) {
		return em.find(CreditCard.class, id);
	}

	@Override
	public Boolean destroy(Integer id) {
		em.remove(em.find(CreditCard.class, id));
		if (em.find(CreditCard.class, id) == null) {
			return true;
		}
		return false;
	}

	@Override
	public CreditCard create(Integer userId, CreditCard c) {
		CreditCard newCard = c;
		newCard.setUser(userDAO.show(userId));
		em.persist(newCard);
		em.flush();
		return newCard;
	}

	@Override
	public CreditCard update(Integer id, CreditCard c) {
		CreditCard creditCard = em.find(CreditCard.class, id);
		creditCard.setAddress(c.getAddress());
		creditCard.setCreditCardNumber(c.getCreditCardNumber());
		creditCard.setCvv(c.getCvv());
		creditCard.setExpirationDate(c.getExpirationDate());
		return creditCard;
	}
}
