package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@OneToMany(mappedBy="lister")
	private List<ParkingSpot> parkingSpot;
	
	@JsonIgnore
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
		return parkingSpot;
	}

	public void setParkingSpot(List<ParkingSpot> parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	// toString
	@Override
	public String toString() {
		return "Lister [id=" + id + ", socialSecurity=" + socialSecurity + ", payPalAccount=" + payPalAccount
				+ ", user=" + user + ", parkingSpot=" + parkingSpot + "]";
	}
}
