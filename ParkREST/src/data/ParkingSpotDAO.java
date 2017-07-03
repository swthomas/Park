package data;

import java.util.List;

import entities.ParkingSpot;

public interface ParkingSpotDAO {
	
	public List<ParkingSpot> index();
	public ParkingSpot show(Integer id);
	public ParkingSpot create(Integer listerId, ParkingSpot p);
	public ParkingSpot update(Integer id, ParkingSpot parkingSpot);
	public Boolean destroy(Integer id);
	public List<ParkingSpot> initialLoad(Double lat, Double lng);
	public List<ParkingSpot> distanceTEST(Double lat, Double lng);
	public List<ParkingSpot> reservationTEST();
}
