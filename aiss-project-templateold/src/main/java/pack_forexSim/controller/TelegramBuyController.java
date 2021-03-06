package pack_forexSim.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.resource.ClientResource;

import pack_forexSim.model.newsApi.News;
import pack_forexSim.model.telegram.*;

public class TelegramBuyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String token = "1041962767:AAHwtPukiSrAsJrPhRxRvYggYoHsYFAz5EI";
    private static final String chatId = "-343798759";

    public TelegramBuyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//See if message possible to send
		String dq =request.getParameter("quantity");
		String dx =request.getParameter("product");
		String userName = request.getParameter("user");
		System.out.println(dq+" "+dx);
		if ((dq!="")&&(dx!=null)) {
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
		else {
			response.sendRedirect("errorPages/errorTelegramBuy.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
