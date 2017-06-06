package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Lister;
import entities.PaymentToLister;

@Transactional
@Repository
public class PaymentToListerDAOImpl implements PaymentToListerDAO{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public PaymentToLister create(Integer listerId, PaymentToLister payment) {
		payment.setLister(em.find(Lister.class, listerId));
		em.persist(payment);
		em.flush();
		return payment;
	}

	@Override
	public PaymentToLister show(Integer paymentId) {
		return em.find(PaymentToLister.class, paymentId);
	}

	@Override
	public List<PaymentToLister> index(Integer listerId) {
		String q = "SELECT p FROM PaymentToLister p WHERE p.lister.id = :listerId";
		return em.createQuery(q, PaymentToLister.class).setParameter("listerId", listerId).getResultList();
	}
	

}
