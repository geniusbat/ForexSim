package pack_forexSim.model.finantialModellingPrep;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;


public class StockResource {

	private static final Logger log = Logger.getLogger(StockResource.class.getName());
	
	public PriceHistorySearch getHistory(String ticker) throws UnsupportedEncodingException{
		
		String query = URLEncoder.encode(ticker,"UTF-8");
		String uri = "https://financialmodelingprep.com/api/v3/historical-price-full/" + query + "?timeseries=20";
		log.log(Level.FINE,"financialmodelingprep uri: " + uri);
		ClientResource cr = new ClientResource(uri);
		PriceHistorySearch search = cr.get(PriceHistorySearch.class);
		return search;
		
	}
}
