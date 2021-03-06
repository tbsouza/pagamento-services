package br.inatel.dm112.services;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.dm112.interfaces.OrderInterface;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.Orders;
import br.inatel.dm112.model.ResponseStatus;
import br.inatel.dm112.model.dao.OrderDAO;
import br.inatel.dm112.model.entities.Order;

//Acessar via: http://localhost:8080/soap

@WebService(name = "orderService")
public class OrderImplSOAP implements OrderInterface {

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/order/{orderNumber}")
	public Order getOrder(@PathParam("orderNumber") int orderNumber) {
		try {
			return new OrderDAO().getOrderById(orderNumber);
		} catch(IllegalArgumentException  e) {
			return null;
		}
			
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/orders/{cpf}")
	public Orders searchOrders(@PathParam("cpf") String cpf) {
		Orders orders = new Orders();
		orders.setOrders(new OrderDAO().getOrdersByCPF(cpf));
		return orders;
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateOrder")
	public OrderResponse updateOrder(Order order) {
		OrderDAO dao = new OrderDAO();
		
		Order persisted = dao.getOrderById(order.getNumber());
		if (persisted != null) {
			dao.updateOrder(order);
			return new OrderResponse(ResponseStatus.OK.ordinal());
		} else {
			System.out.println("OrderImpl updateOrder - order não encontrado com número: " + order.getNumber());
			dao.insert(order);
			return new OrderResponse(ResponseStatus.ERROR.ordinal());
		}
	}

}
