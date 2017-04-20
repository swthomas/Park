package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Vehicle;

@Transactional
@Repository
public class VehicleDAOImpl implements VehicleDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Vehicle> index() {
		String v = "SELECT v FROM Vehicle v";
		return em.createQuery(v, Vehicle.class).getResultList();
	}

	@Override
	public Vehicle show(int id) {
		return em.find(Vehicle.class, id);
	}

	@Override
	public Vehicle create(Vehicle v) {
		em.persist(v);
		em.flush();
		return v;
	}

	@Override
	public Vehicle update(int id, Vehicle v) {
		Vehicle vehManaged = em.find(Vehicle.class, id);
		vehManaged.setColor(v.getColor());
		vehManaged.setLicensePlate(v.getLicensePlate());
		vehManaged.setMake(v.getMake());
		return vehManaged;
	}

	@Override
	public boolean destroy(int id) {
		em.remove(em.find(Vehicle.class, id));
		if (em.find(Vehicle.class, id) == null) {
			return true;
		}
		return false;
	}
}
