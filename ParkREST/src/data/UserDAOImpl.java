package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CreditCard;
import entities.Lister;
import entities.Reservation;
import entities.User;
import entities.UserPayment;
import entities.Vehicle;

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
	public User create(User user) {
		List<Vehicle> vehicles = new ArrayList<>();
		List<CreditCard> creditCards = new ArrayList<>();
		List<Reservation> reservations = new ArrayList<>();
		List<UserPayment> userPayments = new ArrayList<>();
		user.setCreditCard(creditCards);
		user.setReservations(reservations);
		user.setVehicles(vehicles);
		user.setUserPayments(userPayments);
		em.persist(user);
		em.flush();
		return user;
	}
	
	@Override
	public User update(Integer id, User u) {
		User user = em.find(User.class, id);
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setPhoneNumber(u.getPhoneNumber());
		return user;
	}

	@Override
	public Boolean destroy(Integer id) {
		em.remove(em.find(User.class, id));
		if (em.find(CreditCard.class, id) == null) {
			return true;
		}
		return false;
	}
	
	// listers
	@Override
	public Lister listersIndex(Integer userId) {
		String query = "SELECT l FROM Lister l WHERE l.user.id = :userId";
		return em.createQuery(query, Lister.class).setParameter("userId", userId).getSingleResult();
	}
	
	// vehicles
	@Override
	public List<Vehicle> vehiclesIndex(Integer userId) {
		String query = "SELECT v FROM Vehicle v WHERE v.user.id = :userId";
		return em.createQuery(query, Vehicle.class).setParameter("userId", userId).getResultList();
	}
	
	// creditCards
	@Override
	public List<CreditCard> creditCardsIndex(Integer userId) {
		String query = "SELECT c FROM CreditCard c WHERE c.user.id = :userId";
		return em.createQuery(query, CreditCard.class).setParameter("userId", userId).getResultList();
	}
	
	// reservations
	@Override
	public List<Reservation> reservationsIndex(Integer userId) {
		String query = "SELECT r FROM Reservation r WHERE r.user.id = :userId";
		return em.createQuery(query, Reservation.class).setParameter("userId", userId).getResultList();
	}

	// userPayments
	@Override
	public List<UserPayment> userPaymentsIndex(Integer userId) {
		String query = "SELECT p FROM UserPayment p WHERE p.user.id = :userId";
		return em.createQuery(query, UserPayment.class).setParameter("userId", userId).getResultList();
	}
}
