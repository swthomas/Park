package data;

import java.util.List;

import entities.PaymentToLister;

public interface PaymentToListerDAO {

	public PaymentToLister create(Integer listerId, PaymentToLister p);
	public PaymentToLister show(Integer id);
	public List<PaymentToLister> index(Integer listerId);
}
