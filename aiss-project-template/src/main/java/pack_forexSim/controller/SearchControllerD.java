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


/**
 * Servlet implementation class SearchController
 */
public class SearchControllerD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(SearchControllerD.class.getName());
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchControllerD() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String query = request.getParameter("searchQuery");
		RequestDispatcher rd = null;
		
		//Search for stock prices
		log.log(Level.FINE, "Searching for recent stock prices of" + query);
		StockResource financialmodelingprep = new StockResource();
		PriceHistorySearch prices = financialmodelingprep.getHistory(query);
		
		if(prices!=null) {
			List<String> dates = new ArrayList<String>();
			List<Double> values = new ArrayList<Double>();
			List<Historical> stonks = prices.getHistorical();
			for(Historical stonk: stonks) {
				values.add(Double.valueOf(stonk.getClose()));
				dates.add(stonk.getDate());
			}
			rd = request.getRequestDispatcher("/stockSuccess.jsp");
			request.setAttribute("prices", prices.getHistorical());
		}
		else {
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
