package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstName;

	private String lastName;

	private Double phoneNumber;

	private String email;

	private String username;

	private String password;

	private Boolean isLister;
	
	@JsonIgnore
	@OneToOne(mappedBy="user")
	private Lister lister;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Vehicle> vehicles;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<ParkingTag> parkingTags;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<CreditCard> creditCards;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Reservation> reservations;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<UserPayment> userPayments;

	// gets and sets
	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsLister() {
		return isLister;
	}

	public void setIsLister(Boolean isLister) {
		this.isLister = isLister;
	}

	public Lister getLister() {
		return lister;
	}

	public void setLister(Lister lister) {
		this.lister = lister;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<ParkingTag> getParkingTags() {
		return parkingTags;
	}

	public void setParkingTags(List<ParkingTag> parkingTags) {
		this.parkingTags = parkingTags;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCard(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<UserPayment> getUserPayments() {
		return userPayments;
	}

	public void setUserPayments(List<UserPayment> userPayments) {
		this.userPayments = userPayments;
	}

	// toString
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", username=" + username + ", password=" + password + ", isLister=" + isLister
				+ ", lister=" + lister + ", vehicles=" + vehicles + ", parkingTags=" + parkingTags + ", creditCards="
				+ creditCards + ", reservations=" + reservations + ", userPayments=" + userPayments + "]";
	}
}