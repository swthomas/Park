package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer year;
	
	private String color;
	
	private String make;
	
	private String model;
	
	private String licensePlate;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	// gets and sets
	public Integer getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
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
		return "Vehicle [id=" + id + ", year=" + year + ", color=" + color + ", make=" + make + ", model=" + model
				+ ", licensePlate=" + licensePlate + ", user=" + user + "]";
	}
}
