package data;

import entities.ParkingSpot;

public interface ParkingSpotDAO {
	
	public ParkingSpot show(Integer id);
	public ParkingSpot create(Integer listerId, Integer addressId, ParkingSpot p);
	public ParkingSpot update(Integer id, ParkingSpot parkingSpot);
	public Boolean destroy(Integer id);
}
