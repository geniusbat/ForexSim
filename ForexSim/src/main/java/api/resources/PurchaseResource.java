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


import aiss.model.repository.Purchase;
import aiss.model.repository.MapUserRepository;
import aiss.model.repository.UserRepository;
import api.resources.comparators.ComparatorDatePurchase;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Path("/purchases")
public class PurchaseResource {
	
	public static PurchaseResource _instance=null;
	UserRepository repository;
	
	private PurchaseResource(){
		repository=MapUserRepository.getInstance();
	}
	
	public static PurchaseResource getInstance()
	{
		if(_instance==null)
			_instance=new PurchaseResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Purchase> getAll( @QueryParam("q") String q)
	{
		List<Purchase> result = new ArrayList<Purchase>();
        
        for (Purchase purchase: repository.getAllPurchases()) {
            if (q == null
            		|| purchase.getTicker().toLowerCase().contains(q.toLowerCase())
            		|| purchase.getDate().toLowerCase().contains(q.toLowerCase()))
            	result.add(purchase);
        }
        
        Collections.sort(result, new ComparatorDatePurchase());

        return result;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Purchase get(@PathParam("id") String purchaseId)
	{
		Purchase list = repository.getPurchase(purchaseId);
		
		if (list == null) {
			throw new NotFoundException("The purchase with id="+  purchaseId +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPurchase(@Context UriInfo uriInfo, Purchase  purchase) {
		if (purchase.getTicker() == null || "".equals( purchase.getTicker()))
			throw new BadRequestException("You must pecify the ticker");

		repository.addPurchase(purchase);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build( purchase.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity( purchase);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updatePurchase(Purchase purchase) {
		Purchase oldvalue = repository.getPurchase(purchase.getId());
		if (oldvalue == null) {
			throw new NotFoundException("The purchase with id="+ purchase.getId() +" was not found");			
		}
		
		if (purchase.getTicker()!=null)
			oldvalue.setTicker(purchase.getTicker());
		
		if (purchase.getQuantity()!=null)
			oldvalue.setQuantity(purchase.getQuantity());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removePurchase(@PathParam("id") String purchaseId) {
		Purchase toberemoved=repository.getPurchase(purchaseId);
		if (toberemoved == null)
			throw new NotFoundException("The purchase with ="+ purchaseId +" was not found");
		else
			repository.deletePurchase(purchaseId);
		
		return Response.noContent().build();
	}

}
