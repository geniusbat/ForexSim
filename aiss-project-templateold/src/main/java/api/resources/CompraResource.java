package api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;


import aiss.model.repository.Compra;
import aiss.model.repository.MapUserRepository;
import aiss.model.repository.UserRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path("/purchases")
public class CompraResource {
	
	public static CompraResource _instance=null;
	UserRepository repository;
	
	private CompraResource(){
		repository=MapUserRepository.getInstance();
	}
	
	public static CompraResource getInstance()
	{
		if(_instance==null)
			_instance=new CompraResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Compra> getAll( @QueryParam("q") String q)
	{
		List<Compra> result = new ArrayList<Compra>();
        
        for (Compra compra: repository.getAllCompras()) {
            if (q == null
            		|| compra.getTicker().toLowerCase().contains(q.toLowerCase()))
            	result.add(compra);
        }

        return result;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Compra get(@PathParam("id") String compraId)
	{
		Compra list = repository.getCompra(compraId);
		
		if (list == null) {
			throw new NotFoundException("The purchase with id="+ compraId +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addCompra(@Context UriInfo uriInfo, Compra compra) {
		if (compra.getTicker() == null || "".equals(compra.getTicker()))
			throw new BadRequestException("You must pecify the ticker");

		repository.addCompra(compra);

		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(compra.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(compra);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateCompra(Compra compra) {
		Compra oldvalue = repository.getCompra(compra.getId());
		if (oldvalue == null) {
			throw new NotFoundException("The purchase with id="+ compra.getId() +" was not found");			
		}
		
		// Update title
		if (compra.getTicker()!=null)
			oldvalue.setTicker(compra.getTicker());
		
		// Update year
		if (compra.getCantidad()!=null)
			oldvalue.setCantidad(compra.getCantidad());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeCompra(@PathParam("id") String compraId) {
		Compra toberemoved=repository.getCompra(compraId);
		if (toberemoved == null)
			throw new NotFoundException("The purchase with ="+ compraId +" was not found");
		else
			repository.deleteCompra(compraId);
		
		return Response.noContent().build();
	}
}
