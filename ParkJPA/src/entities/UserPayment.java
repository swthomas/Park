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
public class UserPayment {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Double rate;
	
	@Temporal(TemporalType.DATE)
	private Date date;
		
	private Double amount;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="parkingSpotId")
	private ParkingSpot parkingSpot;
	
	@JsonIgnore
	@OneToOne(mappedBy="userPayment")
	private PaymentToLister paymentToLister;
	
	// gets and sets
	public Integer getId() {
		return id;
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

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public PaymentToLister getPaymentToLister() {
		return paymentToLister;
	}

	public void setPaymentToLister(PaymentToLister paymentToLister) {
		this.paymentToLister = paymentToLister;
	}
	
	// toString
	@Override
	public String toString() {
		return "UserPayment [id=" + id + ", rate=" + rate + ", date=" + date + ", amount=" + amount + ", user=" + user
				+ ", parkingSpot=" + parkingSpot + ", paymentToLister=" + paymentToLister + "]";
	}

	
}
