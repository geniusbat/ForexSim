package selfTest;

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

import com.google.appengine.repackaged.com.google.gson.Gson;

import pack_forexSim.model.newsApi.Article;
import pack_forexSim.model.newsApi.News;


public class TestNewsApiClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NEWS_API_KEY = "f9c8b0f04dc84791949809dc7e021880";

    public TestNewsApiClass() {
        super();
    }
    

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd = null;
	String uri = "http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=f9c8b0f04dc84791949809dc7e021880";
	// Search for movies in OMDb
	String omdb;
	//omdb = new ClientResource(uri).get(Article.class).getAuthor();
	ClientResource cr = new ClientResource(uri);

	ObjectMapper objectMapper = new ObjectMapper();

	News claseJava = objectMapper.readValue(cr.get(String.class), News.class);
	omdb= claseJava.getArticles().get(0).getTitle();
	rd = request.getRequestDispatcher("/indexpage.jsp");
	List<Article> listaNoticias = new ArrayList<Article>();
	listaNoticias.add(claseJava.getArticles().get(0));
	listaNoticias.add(claseJava.getArticles().get(1));
	request.setAttribute("noticias", listaNoticias);
	rd.forward(request, response);
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
}


	public static void main(String[] args) throws JsonParseException, JsonMappingException, ResourceException, IOException {
		String uri = "http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=f9c8b0f04dc84791949809dc7e021880";
		// Search for movies in OMDb
		String omdb;
		//omdb = new ClientResource(uri).get(Article.class).getAuthor();
		ClientResource cr = new ClientResource(uri);

		ObjectMapper objectMapper = new ObjectMapper();

		News claseJava = objectMapper.readValue(cr.get(String.class), News.class);
		omdb= claseJava.getArticles().get(0).getTitle();
		List<Article> listaNoticias = new ArrayList<Article>();
		listaNoticias.add(claseJava.getArticles().get(0));
		System.out.println(listaNoticias.get(0).getTitle());
	}

}
