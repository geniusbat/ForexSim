package pack_forexSim.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import pack_forexSim.model.newsApi.Article;
import pack_forexSim.model.newsApi.News;

public class NoticiasController  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String NEWS_API_KEY = "f9c8b0f04dc84791949809dc7e021880";
    public NoticiasController() {
        super();
    }
    
    public static void getNoticias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String uri = "http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey="+NEWS_API_KEY;
		ClientResource cr = new ClientResource(uri);
		ObjectMapper objectMapper = new ObjectMapper();
		News noticias = objectMapper.readValue(cr.get(String.class), News.class);
		List<Article> listaNoticias = new ArrayList<Article>();
		listaNoticias.add(noticias.getArticles().get(0));
		listaNoticias.add(noticias.getArticles().get(1));
		request.setAttribute("noticias", listaNoticias);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Set up noticias
		RequestDispatcher rd = null;
		String uri = "http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey="+NEWS_API_KEY;
		ClientResource cr = new ClientResource(uri);
		ObjectMapper objectMapper = new ObjectMapper();
		News noticias = objectMapper.readValue(cr.get(String.class), News.class);
		rd = request.getRequestDispatcher("/moreNews.jsp");
		List<Article> listaNoticias = new ArrayList<Article>();
		listaNoticias.addAll(noticias.getArticles());
		request.setAttribute("noticias", listaNoticias);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
