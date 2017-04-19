package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.ParkingSensor;
import entities.ParkingSpot;

@Transactional
@Repository
public class ParkingSensorDAOImpl implements ParkingSensorDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ParkingSensor show(Integer id) {
		return em.find(ParkingSensor.class, id);
	}

	@Override
	public ParkingSensor create(Integer parkingSpotId, ParkingSensor sensor) {
		ParkingSensor newSensor = sensor;
		newSensor.setParkingSpot(em.find(ParkingSpot.class, parkingSpotId));
		em.persist(newSensor);
		em.flush();
		return newSensor;
	}

	@Override
	public ParkingSensor update(Integer sensorId, ParkingSensor sensor) {
		// DO WE NEED?
		return null;
	}

	@Override
	public Boolean destroy(Integer sensorId) {
		ParkingSensor sensor = em.find(ParkingSensor.class, sensorId);
		em.remove(sensor);
		if (em.find(ParkingSensor.class, sensorId) == null) {
			return true;
		}
		return false;
	}

}
