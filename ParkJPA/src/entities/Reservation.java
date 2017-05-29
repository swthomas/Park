package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date reservedFromDate;
	
	private Date reservedToDate;
	
	private Double rate;
	
	@JsonIgnore
	@OneToOne
    @JoinColumn(name="parkingSpotId")
	private ParkingSpot parkingSpot;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	// gets and sets
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

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// toString
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservedFromDate=" + reservedFromDate + ", reservedToDate=" + reservedToDate
				+ ", rate=" + rate + ", parkingSpot=" + parkingSpot + ", user=" + user
				+ "]";
	}	
}

