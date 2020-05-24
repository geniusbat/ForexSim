package test;

import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

import pack_forexSim.model.finantialModellingPrep.PriceHistorySearch;
import pack_forexSim.model.finantialModellingPrep.StockResource;

public class FinancialModelingPrepTest {
	
	@Test
	public void successfulSearchTest() throws UnsupportedEncodingException {
		String ticker = "GOOGL";
		StockResource stock = new StockResource();
		PriceHistorySearch stockResults = stock.getHistory(ticker);
		
		assertNotNull("The search returned null", stockResults);
		assertNotNull("The search returned null", stockResults.getSymbol());
		assertEquals(ticker, stockResults.getSymbol());
		assertNotNull("The search returned null", stockResults.getHistorical());
		assertNotNull("The search returned null", stockResults.getHistorical().get(0));


	}
	
	@Test
	public void invalidSearchTest() throws UnsupportedEncodingException {
		String ticker = "google";
		StockResource stock = new StockResource();
		PriceHistorySearch stockResults = stock.getHistory(ticker);
		
		assertNull("The search returned something", stockResults.getSymbol());
		assertNull("The search returned something", stockResults.getHistorical());

	}

}
