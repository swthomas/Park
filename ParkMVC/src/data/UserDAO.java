package data;

import entities.User;

public interface UserDAO {
	public User createNewUser(User user);
	public User getUserById(Integer id);
}
