package aiss.model.repository;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String id;
	private String name;
	private Double cash;
	private List<Compra> compras;
	
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

	public List<Compra> getCompras() {
		return compras;
	}
	
	public Compra getCompra(String id) {
		if(compras==null) {
			return null;
		}
		Compra compra = null;
		for(Compra c: compras)
			if(c.getId().equals(id)) {
				compra=c;
			}
		return compra;
	}
	
	public void addCompra(Compra c) {
		if(compras==null)
			compras = new ArrayList<Compra>();
		compras.add(c);
	}
	
	public void deleteCompra(Compra c) {
		compras.remove(c);
	}
	public void deleteCompra(String id) {
		Compra c = getCompra(id);
		if(c!=null) {
			compras.remove(c);
		}
	}
}
