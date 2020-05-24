package pack_forexSim.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.resource.ClientResource;

import pack_forexSim.model.finantialModellingPrepForex.Historical;
import pack_forexSim.model.finantialModellingPrepForex.ResourceForex;

public class SearchControllerForex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(SearchController.class.getName());
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchControllerForex() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	
	String query = request.getParameter("searchQuery");
	RequestDispatcher rd = null;
	
	ResourceForex fm = new ResourceForex();
	Historical fmResults = fm.getHistory(query);

	if (fmResults!=null){
		rd = request.getRequestDispatcher("/indexpage.jsp");
		request.setAttribute("date", fmResults.getDate());
		request.setAttribute("close", fmResults.getClose());
		request.setAttribute("high", fmResults.getHigh());
		request.setAttribute("low", fmResults.getLow());
		request.setAttribute("open", fmResults.getOpen());
		request.setAttribute("volume", fmResults.getVolume());
	} else {
		rd = request.getRequestDispatcher("errorPages/errorSearchController.jsp");
	}
	rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
