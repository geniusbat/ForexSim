package pack_forexSim.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.resource.ClientResource;

import aiss.model.repository.MapUserRepository;
import aiss.model.repository.Purchase;
import aiss.model.repository.User;

public class TelegramBuyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String token = "1041962767:AAHwtPukiSrAsJrPhRxRvYggYoHsYFAz5EI";
    private static final String chatId = "-343798759";

    public TelegramBuyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//See if message possible to send
		MapUserRepository repository = MapUserRepository.getInstance();
		String dq =request.getParameter("quantity");
		Double price = Double.valueOf(dq);
		String dx =request.getParameter("product");
		String userName = request.getParameter("user");
		Double available = 0.0;
		System.out.println(dq+" "+dx);
			
			if (dq!="" && dx!=null && !dx.equals("")) {
				Purchase c = new Purchase();
				c.setTicker(dx);
				c.setQuantity(Double.valueOf(dq));
				c.setDate(LocalDate.now().toString());
				repository.addPurchase(c);
				boolean compraExito = false;
				boolean usuarioExiste = false;
				for(User u : repository.getAllUsers()){
					if(u.getName().equals(userName)) {
						usuarioExiste = true;
						available = u.getCash();
						if(available>=price) {
							u.setCash(u.getCash()-Double.valueOf(dq));
							repository.addPurchase(u.getId(),c.getId());
							compraExito = true;
							break;
						}
					}
				}
				if(compraExito==false && usuarioExiste==false){
					User _u = new User();
					_u.setName(userName);
					_u.setCash(1000.0-Double.valueOf(dq));
					repository.addUser(_u);
					repository.addPurchase(_u.getId(),c.getId());
					compraExito = true;
				}
				if(compraExito==false) {
					RequestDispatcher rd = null;
					String cadena = "Sorry, " + userName + ", you have " + String.valueOf(available) + " dollars, and you are trying to spend " + String.valueOf(price) + ".";
					request.setAttribute("cadena", cadena);
					rd = request.getRequestDispatcher("/errorPages/errorRepositoryBuy.jsp");
					rd.forward(request, response);
				}
				if(compraExito==true) {
					ClientResource crA = new ClientResource("https://api.telegram.org/bot"+token+"/getUpdates");
					ObjectMapper objectMapperA = new ObjectMapper();
					//Messages resultado = objectMapperA.readValue(crA.get(String.class), Messages.class);
					//Send message
					//if (resultado.getOk()) {
					String uri = "https://api.telegram.org/bot"+token+"/sendMessage?chat_id="+chatId+"&text=";
					RequestDispatcher rd = null;
					Integer cantidad = Integer.parseInt(dq);
					String producto = dx;
					if (cantidad>0) {
						String cadena = userName +" bought "+String.valueOf(cantidad)+" quantity of "+ producto;
						uri = uri+URLEncoder.encode(cadena,"UTF-8");
						//TODO enviar al cliente
						request.setAttribute("UserBuy",cadena);
						ClientResource crB = new ClientResource(uri);
						crB.get();
						rd = request.getRequestDispatcher("/telegramSuccess.jsp");
						rd.forward(request, response);
					}
					else {
						response.sendRedirect("errorPages/errorTelegramBuy.html");
					}
				//}
				}
			}
			else {
				response.sendRedirect("errorPages/errorTelegramBuy.html");
			}
			
		}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
