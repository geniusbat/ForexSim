package pack_forexSim.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack_forexSim.model.finantialModellingPrep.Historical;
import pack_forexSim.model.finantialModellingPrep.PriceHistorySearch;
import pack_forexSim.model.finantialModellingPrep.StockResource;

public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(SearchControllerD.class.getName());
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    private static String getStringFromArray(Double array[]) {
    	String r = "";
    	int i = 0; 
    	while (i< array.length) {
    		if (i==0) {
    			r+=String.valueOf(array[i]);
    		}
    		else {
    			r+=","+String.valueOf(array[i]);
    		}
    		i++;
    	}
    	return r;
    }
    private static String getStringFromArray(String array[]) {
    	String r = "";
    	int i = 0; 
    	while (i< array.length) {
    		if (i==0) {
    			r+=array[i];
    		}
    		else {
    			r+=","+array[i];
    		}
    		i++;
    	}
    	return r;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String query = request.getParameter("searchQuery");
		RequestDispatcher rd = null;
		
		if (query!="") {
			StockResource financialmodelingprep = new StockResource();
			PriceHistorySearch prices = financialmodelingprep.getHistory(query);
			try {
				List<String> dates = new ArrayList<String>();
				List<Double> values = new ArrayList<Double>();
				List<Historical> stonks = prices.getHistorical();
				for(Historical stonk: stonks) {
					values.add(Double.valueOf(stonk.getClose()));
					dates.add(stonk.getDate());
				}
				rd = request.getRequestDispatcher("/indexpage.jsp");
				Double arrayP[] = new Double[prices.getHistorical().size()];
				String arrayD[] = new String[prices.getHistorical().size()];
				int i = 0;
				while (i < prices.getHistorical().size()) {
					Historical el = prices.getHistorical().get(i);
					arrayP[i] = el.getClose();
					arrayD[i] = el.getDate();
					i++;
				}
				System.out.println(getStringFromArray(arrayD));
	 			request.setAttribute("pricesAsString",getStringFromArray(arrayP));
	 			request.setAttribute("datesAsString",getStringFromArray(arrayD));
	 			request.setAttribute("producto", query);
	 			NoticiasController.getNoticias(request,response);
			}
			catch (Exception e) {
				rd = request.getRequestDispatcher("errorPages/errorSearchController.jsp");
			}
			rd.forward(request, response);
		}
		else {
			rd = request.getRequestDispatcher("errorPages/errorSearchController.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
