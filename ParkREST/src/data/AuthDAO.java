package data;

import entities.User;

public interface AuthDAO {
	public User register(User u);

	public User authenticateUser(User u);
}
