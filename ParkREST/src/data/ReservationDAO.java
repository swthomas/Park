package data;

import entities.Address;
import entities.Reservation;

public interface ReservationDAO {
	public Reservation show(Integer id);
	public Boolean destroy(Integer id);
	public Reservation create(Reservation r, Integer uId, Integer pId, Integer cId);
	public Reservation update(Integer id, Reservation a);
}
