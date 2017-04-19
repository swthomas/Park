package entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CreditCard {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer creditCardNumber;
	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	private Integer cvv;
	
	private Boolean activeStatus;
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;
	
	@OneToOne
    @JoinColumn(name="addressId")
	@JsonIgnore
	private Address address;

	// gets and sets
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

	// toString
	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", creditCardNumber=" + creditCardNumber + ", expirationDate=" + expirationDate
				+ ", cvv=" + cvv + ", activeStatus=" + activeStatus + ", user=" + user + ", address=" + address + "]";
	}	
}
