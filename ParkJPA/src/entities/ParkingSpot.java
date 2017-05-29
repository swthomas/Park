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

@Entity
public class ParkingSpot {
	
	// fields 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
		
	private Double rate;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="listerId") 
	private Lister lister;
	
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="addressId")  
	private Address address;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="parkingSensorId")
	private ParkingSensor parkingSensor;
	
	@JsonBackReference
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Photo> photos;
	
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
		return "ParkingSpot [id=" + id + ", description=" + description + ", rate="
				+ rate + ", lister=" + lister + ", address=" + address + "]";
	}
}
