package data;

import entities.Reservation;

public interface ReservationDAO {
	public Reservation show(Integer id);
	public Boolean destroy(Integer id);
	public Reservation create(Reservation r, Integer userId, Integer parkingSpotId);
	public Reservation update(Integer id, Reservation a);
}
