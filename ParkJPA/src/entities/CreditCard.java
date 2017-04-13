package entities;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer creditCardNumber;
	
	private Date expirationDate;
	
	private Integer cvv;
	
	private Boolean activeStatus;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@OneToOne
    @JoinColumn(name="addressId")
	private Address address;

	
	
	
	
	public Integer getId() {
		return id;
	}

	public Integer getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(Integer creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", creditCardNumber=" + creditCardNumber + ", expirationDate=" + expirationDate
				+ ", cvv=" + cvv + ", activeStatus=" + activeStatus + ", user=" + user + ", address=" + address + "]";
	}	
}
