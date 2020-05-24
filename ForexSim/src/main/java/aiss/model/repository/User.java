package aiss.model.repository;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String id;
	private String name;
	private Double cash;
	private List<Purchase> purchases;
	
	public User() {}
	
	public User(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}
	
	public Purchase getPurchase(String id) {
		if(purchases==null) {
			return null;
		}
		Purchase purchase = null;
		for(Purchase p: purchases)
			if(p.getId().equals(id)) {
				purchase=p;
			}
		return purchase;
	}
	
	public void addPurchase(Purchase p) {
		if(purchases==null)
			purchases = new ArrayList<Purchase>();
		purchases.add(p);
	}
	
	public void deletePurchase(Purchase p) {
		purchases.remove(p);
	}
	public void deletePurchase(String id) {
		Purchase p = getPurchase(id);
		if(p!=null) {
			purchases.remove(p);
		}
	}
}
