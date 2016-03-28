package ca.intelliware.java8lab.transaction;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TransactionInquiryTest {
	
	private TransactionInquiryJ8 fixture;
	
	@Before
	public void setUp() {
		fixture = new TransactionInquiryJ8();
	}

    @Test
    public void testFindTransactionsFrom2012() {

        long result = fixture.findNumberOfTransactionsFrom2012();

        assertEquals(4, result);
    }

	@Test
	public void testFindTransactionsFrom2011() {
		
		List<Transaction> result = fixture.findTransactionsFrom2011();
		
		assertEquals(2, result.size());	
		assertEquals(2011, result.get(0).getYear());
		assertEquals(300, result.get(0).getValue());
		assertEquals(2011, result.get(1).getYear());
		assertEquals(400, result.get(1).getValue());
	}
	
	@Test
	public void testFindAllUniqueCities() {
		
		List<String> result = fixture.findAllUniqueCities();
		
		assertEquals(2, result.size());	
		assertEquals("Cambridge", result.get(0));
		assertEquals("Milan", result.get(1));	
	}
	
	@Test
	public void testFindAllTradersFromCambridge() {
		
		List<Trader> result = fixture.findAllTradersFromCambridge();
		
		assertEquals(3, result.size());	
		assertEquals("Alan", result.get(0).getName());
		assertEquals("Brian", result.get(1).getName());
		assertEquals("Raoul", result.get(2).getName());
	}
	
	@Test
	public void testGetAllTradersNames() {
		
		String result = fixture.getAllTradersNames();
		
		assertEquals("AlanBrianMarioRaoul", result);
	}
	
	@Test
	public void testAreThereAnyTradeFromMilan() {
		
		boolean result = fixture.areThereAnyTradeFromMilan();
		
		assertTrue(result);
	}
	
	@Test
	public void testUpdateTradersFromMilanToCambridge(){
		List<Transaction> result = fixture.updateTradersFromMilanToCambridge();
		
		assertEquals(6, result.size());	
		
		assertEquals("Cambridge", result.get(3).getTrader().getCity());	
		assertEquals("Cambridge", result.get(4).getTrader().getCity());	
	}
	
	@Test
	public void testGetHighestTransactionValue() {
		
		int result = fixture.getHighestTransactionValue();
		
		assertEquals(1000, result);
	}

    @Test
    public void testGetTransactionTotal() {
        int result = fixture.getTransactionTotal();

        assertEquals(4060, result);
    }

}
