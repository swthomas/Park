package data;

import java.util.List;

import entities.Reservation;

public interface ReservationDAO {
	public Reservation show(Integer id);
	public List<Reservation> index(Integer userId);
	public Boolean destroy(Integer id);
	public Reservation create(Reservation r, Integer userId, Integer parkingSpotId, Integer vehicleId);
	public Reservation update(Integer id, Reservation a);
}
