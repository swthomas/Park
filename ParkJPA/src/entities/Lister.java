package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Lister {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private Integer socialSecurity;
	
	private String payPalAccount;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="userId")
	private User user;

	@JsonManagedReference(value="listerToParkingSpots")
	@OneToMany(mappedBy="lister", fetch= FetchType.EAGER)
	private List<ParkingSpot> parkingSpots;
	
	@JsonManagedReference
	@OneToOne
    @JoinColumn(name="addressId")
	private Address address;

	// gets and sets
	public Integer getId() {
		return id;
	}

	public Integer getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(Integer socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getPayPalAccount() {
		return payPalAccount;
	}

	public void setPayPalAccount(String payPalAccount) {
		this.payPalAccount = payPalAccount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ParkingSpot> getParkingSpot() {
		return parkingSpots;
	}

	public void setParkingSpot(List<ParkingSpot> parkingSpot) {
		this.parkingSpots = parkingSpot;
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
		return "Lister [id=" + id + ", socialSecurity=" + socialSecurity + ", payPalAccount=" + payPalAccount
				+ ", user=" + user + ", parkingSpot=" + parkingSpots + "]";
	}
}
