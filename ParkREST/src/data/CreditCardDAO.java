package data;

import java.util.List;

import entities.CreditCard;

public interface CreditCardDAO {
	public CreditCard show(Integer id);
	public Boolean destroy(Integer id);
	public CreditCard create(Integer userId, CreditCard c);
	public CreditCard update(Integer id, CreditCard c);
}