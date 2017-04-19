package data;

import java.util.List;

import entities.CreditCard;
import entities.Lister;
import entities.User;

public interface UserDAO {
	public List<User> index();
	public List<CreditCard> creditCardsIndex(Integer userId);
	public Lister listersIndex(Integer userId);
	public User show(Integer id);
	public User show(String username, String password);
	public Boolean destroy(Integer id);
	public User create(User u);
	public User update(User u);
}
