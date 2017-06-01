package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PaymentToLister {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date date;
		
	private Double amount;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="listerId")
	private Lister lister;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="userPaymentId")
	private UserPayment userPayment;

	// gets and sets
	public Integer getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Lister getLister() {
		return lister;
	}

	public void setLister(Lister lister) {
		this.lister = lister;
	}

	public UserPayment getUserPayment() {
		return userPayment;
	}

	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}

	// toString
	@Override
	public String toString() {
		return "PaymentToLister [id=" + id + ", date=" + date + ", amount=" + amount + ", lister=" + lister
				+ ", userPayment=" + userPayment + "]";
	}
}
