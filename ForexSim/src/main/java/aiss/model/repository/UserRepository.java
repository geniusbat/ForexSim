package aiss.model.repository;

import java.util.Collection;

public interface UserRepository {
	
	//Purchases
	public void addPurchase(Purchase c);
	public Collection<Purchase> getAllPurchases();
	public Purchase getPurchase(String purchaseId);
	public void updatePurchase(Purchase c);
	public void deletePurchase(String purchaseId);
	
	//Users
	public void addUser(User u);
	public Collection<User> getAllUsers();
	public User getUser(String userId);
	public void updateUser(User u);
	public void deleteUser(String userId);
	public Collection<Purchase> getAll(String userId);
	public void addPurchase(String userId, String purchaseId);
	public void removePurchase(String userId, String purchaseId);

}
