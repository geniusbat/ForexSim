package pack_forexSim.model.finantialModellingPrepForex;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;;

public class ResourceForex {

	private static final Logger log = Logger.getLogger(ResourceForex.class.getName());
	
	public Historical getHistory(String query) throws UnsupportedEncodingException {
		String queryFormatted = URLEncoder.encode(query, "UTF-8");
		String uri = "https://financialmodelingprep.com/api/v3/historical-chart/1hour/"+queryFormatted; 
		
		log.log(Level.FINE, "financialmodelingprep URI: " + uri);
		
		ClientResource cr = new ClientResource(uri);
		Historical search = cr.get(Historical.class);
		
		return search;
	}
}