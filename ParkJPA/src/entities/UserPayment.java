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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class UserPayment {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Double rate;
	
	@Temporal(TemporalType.DATE)
	private Date date;
		
	private Double amount;

	@ManyToOne
	@JoinColumn(name="userId")
    @JsonBackReference(value="userToUserPayments")
	private User user;

	@OneToOne
	@JoinColumn(name="creditCardId")	
	private CreditCard creditCard;

	@OneToOne
	@JoinColumn(name="parkingSpotId")
	private ParkingSpot parkingSpot;
	
	// gets and sets
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	// toString
	@Override
	public String toString() {
		return "UserPayment [id=" + id + ", rate=" + rate + ", date=" + date + ", amount=" + amount + ", user=" + user
				+ ", creditCard=" + creditCard + ", parkingSpot=" + parkingSpot + "]";
	}
}
