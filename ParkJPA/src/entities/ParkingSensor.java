package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ParkingSensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Boolean occupied;
	
	@OneToOne
	@JoinColumn(name="parkingSpotId")
	private ParkingSpot parkingSpot;
	
	@OneToOne
    @JoinColumn(name="parkingTagId")
	private ParkingTag parkingTag;

	
	
	
	
	public Integer getId() {
		return id;
	}

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

	public ParkingTag getParkingTag() {
		return parkingTag;
	}

	public void setParkingTag(ParkingTag parkingTag) {
		this.parkingTag = parkingTag;
	}

	@Override
	public String toString() {
		return "ParkingSensor [id=" + id + ", occupied=" + occupied + ", parkingSpot=" + parkingSpot + ", parkingTag="
				+ parkingTag + "]";
	}
}	
