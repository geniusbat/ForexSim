package api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;


import aiss.model.repository.Compra;
import aiss.model.repository.MapUserRepository;
import aiss.model.repository.User;
import aiss.model.repository.UserRepository;

@Path("/users")
public class UserResource {
	
	private static UserResource _instance=null;
	UserRepository repository;
	
	private UserResource() {
		repository=MapUserRepository.getInstance();

	}
	
	public static UserResource getInstance()
	{
		if(_instance==null)
				_instance=new UserResource();
		return _instance;
	}
	
	 @GET
	    @Produces("application/json")
	    public Collection<User> getAll(@QueryParam("isEmpty") Boolean isEmpty, @QueryParam("name") String name)
	    {
	        List<User> result = new ArrayList<User>();
	            
	        for (User user: repository.getAllUsers()) {
	            if (name == null || user.getName().equals(name)) { // Name filter
	                if (isEmpty == null // Empty playlist filter
	                        || (isEmpty && (user.getCompras() == null || user.getCompras().size() == 0))
	                        || (!isEmpty && (user.getCompras() != null && user.getCompras().size() > 0))) {
	                    result.add(user);
	                }
	            }
	        }

	        return result;
	    }
	 
	 @GET
		@Path("/{id}")
		@Produces("application/json")
		public User get(@PathParam("id") String id)
		{
			User list = repository.getUser(id);
			
			if (list == null) {
				throw new NotFoundException("The user with id="+ id +" was not found");			
			}
			
			return list;
		}
	 
	 @POST
		@Consumes("application/json")
		@Produces("application/json")
		public Response addPlaylist(@Context UriInfo uriInfo, User user) {
			if (user.getName() == null || "".equals(user.getName()))
				throw new BadRequestException("The name of the playlist must not be null");
			
			if (user.getCompras()!=null)
				throw new BadRequestException("The purchases property is not editable.");

			repository.addUser(user);

			// Builds the response. Returns the playlist the has just been added.
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(user.getId());
			ResponseBuilder resp = Response.created(uri);
			resp.entity(user);			
			return resp.build();
		}
	 
	 @PUT
		@Consumes("application/json")
		public Response updateUser(User user) {
			User olduser = repository.getUser(user.getId());
			if (olduser == null) {
				throw new NotFoundException("The user with id="+ user.getId() +" was not found");			
			}
			
			if (user.getCompras()!=null)
				throw new BadRequestException("The purchases property is not editable.");
			
			// Update name
			if (user.getName()!=null)
				olduser.setName(user.getName());
			
			// Update description
			if (user.getCash()!=null)
				olduser.setCash(user.getCash());
			
			return Response.noContent().build();
		}
	 
	 @DELETE
		@Path("/{id}")
		public Response removeUser(@PathParam("id") String id) {
			User toberemoved=repository.getUser(id);
			if (toberemoved == null)
				throw new NotFoundException("The user with id="+ id +" was not found");
			else
				repository.deleteUser(id);
			
			return Response.noContent().build();
		}
	 
	 @POST	
		@Path("/{userId}/{purchaseId}")
		@Produces("application/json")
		public Response addCompra(@Context UriInfo uriInfo,@PathParam("userId") String userId, @PathParam("purchaseId") String compraId)
		{				
			
			User user = repository.getUser(userId);
			Compra compra = repository.getCompra(compraId);
			
			if (user==null)
				throw new NotFoundException("The user with id=" + userId + " was not found");
			
			if (compra == null)
				throw new NotFoundException("The purchase with id=" + compraId + " was not found");
			
			if (user.getCompra(compraId)!=null)
				throw new BadRequestException("The purchase has already been done.");
				
			repository.addCompra(userId, compraId);		

			// Builds the response
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(userId);
			ResponseBuilder resp = Response.created(uri);
			resp.entity(user);			
			return resp.build();
		}
	 
	 @DELETE
		@Path("/{userId}/{purchaseId}")
		public Response removeCompra(@PathParam("userId") String userId, @PathParam("purchaseId") String compraId) {
			User user = repository.getUser(userId);
			Compra compra = repository.getCompra(compraId);
			
			if (user==null)
				throw new NotFoundException("The user with id=" + userId + " was not found");
			
			if (compra == null)
				throw new NotFoundException("The purchase with id=" + compraId + " was not found");
			
			
			repository.removeCompra(userId, compraId);		
			
			return Response.noContent().build();
		}

}
