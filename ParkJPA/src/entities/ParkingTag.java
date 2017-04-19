package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParkingTag {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String serialNumber;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	
	
	
	// gets and sets
	public Integer getId() {
		return id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// toString
	@Override
	public String toString() {
		return "ParkingTag [id=" + id + ", serialNumber=" + serialNumber + ", user=" + user + "]";
	}
}
