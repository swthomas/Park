package data;

import entities.ParkingSensor;

public interface ParkingSensorDAO {
	public ParkingSensor show(Integer id);
	public ParkingSensor create(Integer parkingSpotId, ParkingSensor sensor);
	public ParkingSensor update(Integer sensorId, ParkingSensor sensor);
	public Boolean destroy(Integer sensorId);
}
