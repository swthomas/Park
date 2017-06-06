package entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Photo {

	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	private byte[] image;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="parkingSpotId")
	private ParkingSpot parkingSpot;

	// gets and sets
	public byte[] getimage() {
		return image;
	}

	public void setimage(byte[] image) {
		this.image = image;
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

	// toString
	@Override
	public String toString() {
		return "Photo [id=" + id + ", photo=" + Arrays.toString(image) + ", parkingSpot=" + parkingSpot + "]";
	}
}
