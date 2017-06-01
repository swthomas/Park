package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParkingSensor {
	
	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Boolean occupied;
	
	@JsonBackReference(value="parkingSpotToParkingSensor")
	@OneToOne
    @JoinColumn(name="parkingSpotId")
	private ParkingSpot parkingSpot;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="parkingTagId")
	private ParkingTag parkingTag;

	// gets and sets
	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public Integer getId() {
		return id;
	}
	
	public ParkingTag getParkingTag() {
		return parkingTag;
	}

	public void setParkingTag(ParkingTag parkingTag) {
		this.parkingTag = parkingTag;
	}

	// toString
	@Override
	public String toString() {
		return "ParkingSensor [id=" + id + ", occupied=" + occupied + ", parkingSpot=" + parkingSpot + ", parkingTag="
				+ parkingTag + "]";
	}
}
