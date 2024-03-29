package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ParkingSpotAddress {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String street;
	
	private String street2;
	
	private Integer postalCode;
	
	private String city;
	
	private String state;
	
	private Double latitude;
	
	private Double longitude;

	@JsonBackReference(value="parkingSpotToParkingSpotAddress")
	@OneToMany(mappedBy="parkingSpotAddress", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<ParkingSpot> parkingSpots;

	// gets and sets
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public List<ParkingSpot> getParkingSpots() {
		return parkingSpots;
	}

	public void setParkingSpots(List<ParkingSpot> parkingSpots) {
		this.parkingSpots = parkingSpots;
	}

	public Integer getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "ParkingSpotAddress [id=" + id + ", street=" + street + ", street2=" + street2 + ", postalCode="
				+ postalCode + ", city=" + city + ", state=" + state + ", latitude=" + latitude + ", longitude="
				+ longitude + ", parkingSpots=" + parkingSpots + "]";
	}
}

