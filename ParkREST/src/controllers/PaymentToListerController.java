package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.PaymentToListerDAO;
import entities.PaymentToLister;

@RestController
public class PaymentToListerController {

	@Autowired
	private PaymentToListerDAO paymentDAO;
	
	@RequestMapping(value = "payment/{paymentId}", method = RequestMethod.GET)
	public PaymentToLister show(@PathVariable Integer paymentId) {
		return paymentDAO.show(paymentId);
	}
	
	@RequestMapping(value = "payments/{listerId}", method = RequestMethod.GET)
	public List<PaymentToLister> index(@PathVariable Integer listerId) {
		return paymentDAO.index(listerId);
	}
	
	@RequestMapping(value = "payment/{listerId}", method = RequestMethod.POST)
	public PaymentToLister create(@PathVariable Integer listerId, @RequestBody String jsonPaymentToLister) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			PaymentToLister mappedPayment = mapper.readValue(jsonPaymentToLister, PaymentToLister.class);
			return paymentDAO.create(listerId, mappedPayment);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
