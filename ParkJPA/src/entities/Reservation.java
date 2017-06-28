package entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime reservedFromDate;
	
	private LocalDateTime reservedToDate;
	
	private Double rate;
	
	@OneToOne
	@JoinColumn(name="vehicleId")
	private Vehicle vehicle;
	
	@JsonBackReference(value="parkingSpotToReservation")
	@ManyToOne
    @JoinColumn(name="parkingSpotId")
	private ParkingSpot parkingSpot;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	
	// gets and sets
	public LocalDateTime getReservedFromDate() {
		return reservedFromDate;
	}

	public void setReservedFromDate(LocalDateTime reservedFromDate) {
		this.reservedFromDate = reservedFromDate;
	}

	public LocalDateTime getReservedToDate() {
		return reservedToDate;
	}

	public void setReservedToDate(LocalDateTime reservedToDate) {
		this.reservedToDate = reservedToDate;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

	public Integer getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservedFromDate=" + reservedFromDate + ", reservedToDate=" + reservedToDate
				+ ", rate=" + rate + ", vehicle=" + vehicle + ", parkingSpot=" + parkingSpot + ", user=" + user + "]";
	}
}

