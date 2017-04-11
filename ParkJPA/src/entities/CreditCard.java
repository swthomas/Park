package entities;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer creditCardNumber;
	
	private Date expirationDate;
	
	private Integer cvv;
	
	private Boolean activeStatus;
	
	private User user;
	
	private Address address;;
}
