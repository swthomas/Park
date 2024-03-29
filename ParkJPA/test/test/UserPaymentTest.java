package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.UserPayment;

public class UserPaymentTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("Parkr");
		em = emf.createEntityManager();

	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	// --------------------------------Test go Here--------------------------------//
	
	@Test
	public void test_userPayment_mapping() {
		assertEquals("23.12",em.find(UserPayment.class, 1).getAmount().toString());
	}
	
	
	
	//----------------------------------------------------------------------------// 
	
	@Test
	public void test_true_is_true() {
		boolean pass = true;
		assertEquals(pass, true);
	}
}
