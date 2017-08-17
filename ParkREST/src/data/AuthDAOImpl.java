package data;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.User;

@Transactional
@Repository
@RequestMapping("auth")
public class AuthDAOImpl implements AuthDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User u) {
		String passwordSha = encoder.encode(u.getPassword());
		u.setPassword(passwordSha);
		em.persist(u);
		em.flush();
		return u;
	}

	@Override // add this to after .getPassword:  && !managedUser.getDeactivated()
	public User authenticateUser(User u) throws NoResultException {
		String query = "SELECT u from User u WHERE u.username = :username";
		User managedUser = em.createQuery(query, User.class).setParameter("username", u.getUsername()).getSingleResult();
		if (encoder.matches(u.getPassword(), managedUser.getPassword())) {
			return managedUser;
		} else {
			System.out.println("in null");
			return null;
		}
	}

}
