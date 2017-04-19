package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ParkingSpot {
	
	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	private String pictureURL;
	
	private Double rate;
	
	@OneToOne
    @JoinColumn(name="parkingTagId")
	private ParkingTag parkingTag;

	@ManyToOne
	@JoinColumn(name="listerId") 
	private Lister lister;
	
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

	public ParkingTag getParkingTag() {
		return parkingTag;
	}

	public void setParkingTag(ParkingTag parkingTag) {
		this.parkingTag = parkingTag;
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
				+ rate + ", parkingTag=" + parkingTag + ", lister=" + lister + ", address=" + address + "]";
	}
}
