package data;

import java.util.List;

import entities.CreditCard;
import entities.Lister;
import entities.ParkingTag;
import entities.Reservation;
import entities.User;
import entities.UserPayment;
import entities.Vehicle;

public interface UserDAO {
	public List<User> index();
	public List<Vehicle> vehiclesIndex(Integer userId);
	public List<CreditCard> creditCardsIndex(Integer userId);
	public List<ParkingTag> parkingTagsIndex(Integer userId);
	public List<Reservation> reservationsIndex(Integer userId);
	public List<UserPayment> userPaymentsIndex(Integer userId);
	public Lister listersIndex(Integer userId);
	public User show(Integer id);
	public User show(String username, String password);
	public Boolean destroy(Integer id);
	public User create(User u);
	public User update(Integer id, User u);
}
