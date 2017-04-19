package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Address {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String street;
	
	private String street2;
	
	private Integer postalCode;
	
	private String city;
	
	private String state;

	@OneToMany(mappedBy="address")
	private List<ParkingSpot> parkingSpot;

	// gets and sets
	public Integer getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
		return "Address [id=" + id + ", street=" + street + ", street2=" + street2 + ", postalCode=" + postalCode
				+ ", city=" + city + ", state=" + state + ", parkingSpot=" + parkingSpot + "]";
	}
}
