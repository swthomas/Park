package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private PasswordEncoder encoder;
	
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
		List<Reservation> reservations = new ArrayList<>();
		List<UserPayment> userPayments = new ArrayList<>();
		user.setReservations(reservations);
		user.setVehicles(vehicles);
		user.setUserPayments(userPayments);
		em.persist(user);
		em.flush();
		return user;
	}
	
	@Override
	public User updateNew(Integer id, String userJson) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User mUser = mapper.readValue(userJson, User.class);
			User uUser = em.find(User.class, id);
			
			uUser.setFirstName(mUser.getFirstName());					
			uUser.setLastName(mUser.getLastName());
			uUser.setPhoneNumber(mUser.getPhoneNumber());
			uUser.setIsLister(mUser.getIsLister());
			return uUser;
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public User update(Integer id, String userJson) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User mUser = mapper.readValue(userJson, User.class);
			User uUser = em.find(User.class, id);
			
			JsonNode root = mapper.readTree(userJson); 
			String newPassword = root.at("/password").asText();
			String newPasswordSha = encoder.encode(newPassword); 
					
			uUser.setEmail(mUser.getEmail());
			uUser.setPassword(newPasswordSha);
			System.out.println("**********************************");
			System.out.println(uUser.getPassword());
			return uUser;
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean destroy(Integer id) {
		em.remove(em.find(User.class, id));
		if (em.find(User.class, id) == null) {
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
