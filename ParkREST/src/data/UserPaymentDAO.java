package data;

import entities.UserPayment;

public interface UserPaymentDAO {
	public UserPayment show(Integer id);
	public Boolean destroy(Integer id);
	public UserPayment create(Integer userId, UserPayment u);
	public UserPayment update(Integer id, UserPayment u);
}
