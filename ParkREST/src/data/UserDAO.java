package data;

import java.util.List;

import entities.Lister;
import entities.Reservation;
import entities.User;
import entities.UserPayment;
import entities.Vehicle;

public interface UserDAO {
	public List<User> index();
	public List<Vehicle> vehiclesIndex(Integer userId);
	public List<Reservation> reservationsIndex(Integer userId);
	public List<UserPayment> userPaymentsIndex(Integer userId);
	public Lister listersIndex(Integer userId);
	public User show(Integer id);
	public User show(String username, String password);
	public Boolean destroy(Integer id);
	public User create(User u);
	public User update(Integer id, String userJson);
	public User updateNew(Integer id, String userJson);
	public User updatePassword(Integer id, String userJson);
}
