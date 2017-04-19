package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.CreditCard;
import entities.ParkingTag;

public class ParkingTagTest {
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
	public void test_parkingSpotTag_mapping() {
		assertEquals("1234567890abcd",em.find(ParkingTag.class, 1).getSerialNumber());
	}
	
	
	
	//----------------------------------------------------------------------------// 
	
	@Test
	public void test_true_is_true() {
		boolean pass = true;
		assertEquals(pass, true);
	}
}
