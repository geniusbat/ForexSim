package aiss.model.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



public class MapUserRepository implements UserRepository{

	Map<String, User> userMap;
	Map<String, Purchase> purchaseMap;
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
	purchaseMap = new HashMap<String,Purchase>();
	
	Purchase purchase1 = new Purchase();
	purchase1.setTicker("GOOGL");
	purchase1.setQuantity(100.0);
	purchase1.setDate("2020-01-02");
	addPurchase(purchase1);
	
	Purchase purchase2 = new Purchase();
	purchase2.setTicker("ATVI");
	purchase2.setQuantity(200.0);
	purchase2.setDate("2019-12-31");
	addPurchase(purchase2);
	
	Purchase purchase3 = new Purchase();
	purchase3.setTicker("AAPL");
	purchase3.setQuantity(300.0);
	purchase3.setDate(LocalDate.now().toString());
	addPurchase(purchase3);
	
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
	
	addPurchase(diego.getId(), purchase1.getId());
	addPurchase(gustavo.getId(), purchase2.getId());
	addPurchase(tomas.getId(), purchase3.getId());
	
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
	public void addPurchase(Purchase p) {
		// TODO Auto-generated method stub
		String id = "p" + index2++;
		p.setId(id);
		purchaseMap.put(id, p);
	}


	@Override
	public Collection<Purchase> getAllPurchases() {
		// TODO Auto-generated method stub
		return purchaseMap.values();
	}


	@Override
	public Purchase getPurchase(String purchaseId) {
		// TODO Auto-generated method stub
		return purchaseMap.get(purchaseId);
	}


	@Override
	public void updatePurchase(Purchase p) {
		// TODO Auto-generated method stub
		Purchase purchase = purchaseMap.get(p.getId());
		purchase.setTicker(p.getTicker());
		purchase.setQuantity(p.getQuantity());
	}


	@Override
	public void deletePurchase(String purchaseId) {
		// TODO Auto-generated method stub
		purchaseMap.remove(purchaseId);
	}


	@Override
	public Collection<Purchase> getAll(String userId) {
		// TODO Auto-generated method stub
		return getUser(userId).getPurchases();
	}


	@Override
	public void addPurchase(String userId, String purchaseId) {
		// TODO Auto-generated method stub
		User user = getUser(userId);
		user.addPurchase(purchaseMap.get(purchaseId));
	}


	@Override
	public void removePurchase(String userId, String purchaseId) {
		// TODO Auto-generated method stub
		getUser(userId).deletePurchase(purchaseId);
	}
	
	

}
