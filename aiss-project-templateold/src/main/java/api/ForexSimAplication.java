package api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import api.resources.CompraResource;
import api.resources.UserResource;

public class ForexSimAplication extends Application{

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public ForexSimAplication() {

		singletons.add(UserResource.getInstance());
		singletons.add(CompraResource.getInstance());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
