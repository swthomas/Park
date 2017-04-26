package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParkingSpot {
	
	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	private String pictureURL;
	
	private Double rate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="listerId") 
	private Lister lister;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="addressId")  
	private Address address;

	// gets and sets
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Lister getLister() {
		return lister;
	}

	public void setLister(Lister lister) {
		this.lister = lister;
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
		return "ParkingSpot [id=" + id + ", description=" + description + ", pictureURL=" + pictureURL + ", rate="
				+ rate + ", lister=" + lister + ", address=" + address + "]";
	}
}
