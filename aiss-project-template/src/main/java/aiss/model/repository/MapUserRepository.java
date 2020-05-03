package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapUserRepository implements UserRepository{

	Map<String, User> userMap;
	Map<String, Compra> compraMap;
	private static MapUserRepository instance=null;
	private int index1=0;
	private int index2=0;
	
public static MapUserRepository getInstance() {
		
		if (instance==null) {
			instance = new MapUserRepository();
			instance.init();
		}
		
		return instance;
	}


public void init() {
	
	userMap = new HashMap<String,User>();
	compraMap = new HashMap<String,Compra>();
	
	Compra compra1 = new Compra();
	compra1.setTicker("GOOGL");
	compra1.setCantidad(100.0);
	addCompra(compra1);
	
	Compra compra2 = new Compra();
	compra2.setTicker("ATVI");
	compra2.setCantidad(70.0);
	addCompra(compra2);
	
	Compra compra3 = new Compra();
	compra3.setTicker("AAPL");
	compra3.setCantidad(120.0);
	addCompra(compra3);
	
	User diego = new User();
	diego.setName("Diego");
	diego.setCash(1000.0);
	addUser(diego);
	
	User gustavo = new User();
	gustavo.setName("Gustavo");
	gustavo.setCash(1200.0);
	addUser(gustavo);
	
	User tomas = new User();
	tomas.setName("Tomas");
	tomas.setCash(900000.0);
	addUser(tomas);
	
	addCompra(diego.getId(), compra1.getId());
	addCompra(gustavo.getId(), compra2.getId());
	addCompra(tomas.getId(), compra3.getId());
	
}
	
	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		String id = "u" + index1++;
		u.setId(id);
		userMap.put(id, u);
		
	}

	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userMap.values();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return userMap.get(userId);
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		userMap.put(u.getId(), u);
		
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		userMap.remove(userId);
	}


	@Override
	public void addCompra(Compra c) {
		// TODO Auto-generated method stub
		String id = "c" + index2++;
		c.setId(id);
		compraMap.put(id, c);
	}


	@Override
	public Collection<Compra> getAllCompras() {
		// TODO Auto-generated method stub
		return compraMap.values();
	}


	@Override
	public Compra getCompra(String compraId) {
		// TODO Auto-generated method stub
		return compraMap.get(compraId);
	}


	@Override
	public void updateCompra(Compra c) {
		// TODO Auto-generated method stub
		Compra compra = compraMap.get(c.getId());
		compra.setTicker(c.getTicker());
		compra.setCantidad(c.getCantidad());
	}


	@Override
	public void deleteCompra(String compraId) {
		// TODO Auto-generated method stub
		compraMap.remove(compraId);
	}


	@Override
	public Collection<Compra> getAll(String userId) {
		// TODO Auto-generated method stub
		return getUser(userId).getCompras();
	}


	@Override
	public void addCompra(String userId, String compraId) {
		// TODO Auto-generated method stub
		User user = getUser(userId);
		user.addCompra(compraMap.get(compraId));
	}


	@Override
	public void removeCompra(String userId, String compraId) {
		// TODO Auto-generated method stub
		getUser(userId).deleteCompra(compraId);
	}
	
	

}
