package aiss.model.repository;

import java.util.Collection;

public interface UserRepository {
	
	//Compras
	public void addCompra(Compra c);
	public Collection<Compra> getAllCompras();
	public Compra getCompra(String compraId);
	public void updateCompra(Compra c);
	public void deleteCompra(String compraId);
	
	//Usuarios
	public void addUser(User u);
	public Collection<User> getAllUsers();
	public User getUser(String userId);
	public void updateUser(User u);
	public void deleteUser(String userId);
	public Collection<Compra> getAll(String userId);
	public void addCompra(String userId, String compraId);
	public void removeCompra(String userId, String compraId);

}
