package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date reservedFromDate;
	
	private Date reservedToDate;
	
	private Double rate;
	
	private CreditCard creditCard;
	
	private User user;

	private ParkingSpot parkingSpot;

	
	
	
	
	public Integer getId() {
		return id;
	}

	public Date getReservedFromDate() {
		return reservedFromDate;
	}

	public void setReservedFromDate(Date reservedFromDate) {
		this.reservedFromDate = reservedFromDate;
	}

	public Date getReservedToDate() {
		return reservedToDate;
	}

	public void setReservedToDate(Date reservedToDate) {
		this.reservedToDate = reservedToDate;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
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

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservedFromDate=" + reservedFromDate + ", reservedToDate=" + reservedToDate
				+ ", rate=" + rate + ", creditCard=" + creditCard + ", user=" + user + ", parkingSpot=" + parkingSpot
				+ "]";
	}
}

