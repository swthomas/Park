package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ParkingSpot {
	
	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
		
	private Double rate;
	
	@JsonBackReference(value="listerToParkingSpots")
	@ManyToOne
	@JoinColumn(name="listerId") 
	private Lister lister;
	
	@JsonManagedReference(value="parkingSpotToParkingSpotAddress")
	@OneToOne
	@JoinColumn(name="parkingSpotAddressId")
	private ParkingSpotAddress parkingSpotAddress;
	
	@JsonManagedReference(value="parkingSpotToParkingSensor")
	@OneToOne(mappedBy="parkingSpot", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ParkingSensor parkingSensor;
	
	@JsonIgnore
	@OneToMany(mappedBy="parkingSpot")
	private List<Photo> photos;

	// gets and sets
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public ParkingSpotAddress getParkingSpotAddress() {
		return parkingSpotAddress;
	}

	public void setParkingSpotAddress(ParkingSpotAddress parkingSpotAddress) {
		this.parkingSpotAddress = parkingSpotAddress;
	}

	public ParkingSensor getParkingSensor() {
		return parkingSensor;
	}

	public void setParkingSensor(ParkingSensor parkingSensor) {
		this.parkingSensor = parkingSensor;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Integer getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "ParkingSpot [id=" + id + ", description=" + description + ", rate=" + rate + ", lister=" + lister
				+ ", parkingSpotAddress=" + parkingSpotAddress + ", parkingSensor=" + parkingSensor + ", photos="
				+ photos + "]";
	}
}