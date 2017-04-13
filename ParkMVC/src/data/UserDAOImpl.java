package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.CreditCard;
import entities.ParkingTag;
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
	public User createNewUser(User user) {
		
		User newUser = new User();
		
		List<Vehicle> vehicles = new ArrayList<>();
		List<ParkingTag> parkingTags = new ArrayList<>();
		List<CreditCard> creditCards = new ArrayList<>();
		List<Reservation> reservations = new ArrayList<>();
		List<UserPayment> userPayments = new ArrayList<>();
		
		newUser = user;
		
		newUser.setVehicles(vehicles);
		newUser.setParkingTags(parkingTags);
		newUser.setCreditCard(creditCards);
		newUser.setReservations(reservations);
		newUser.setUserPayments(userPayments);
		
		em.persist(newUser);
		em.flush();
		
		return newUser;
	}

	@Override
	public User getUserById(Integer id) {
		User user = null;
		
		try {
			String q = "SELECT u FROM User u WHERE u.id = :id";
			user = em.createQuery(q, User.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			
		}
		return user;
	}
}
