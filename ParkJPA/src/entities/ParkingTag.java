package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParkingTag {
	
	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer serialNumber;
	
	@JsonIgnore
	@OneToOne
    @JoinColumn(name="userId")
	private User user;

	// gets and sets
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "ParkingTag [id=" + id + ", serialNumber=" + serialNumber + ", user=" + user + "]";
	}
}