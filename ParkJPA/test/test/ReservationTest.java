package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.CreditCard;
import entities.Reservation;

public class ReservationTest {
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
	public void test_reservation_mapping() {
		assertEquals("2017-01-01",em.find(Reservation.class, 1).getReservedFromDate().toString());
	}
	
	
	
	//----------------------------------------------------------------------------// 
	
	@Test
	public void test_true_is_true() {
		boolean pass = true;
		assertEquals(pass, true);
	}
}
