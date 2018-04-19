package br.inatel.dm112.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.interfaces.Logistics;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.Orders;
import br.inatel.dm112.model.ResponseStatus;

@Path("/")
public class LogisticsImpl implements Logistics {

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/startDelivery")
	public Orders startDelivery() {
		OrderClient oc = new OrderClient();

		return oc.getPendentOrders();
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/finishDelivery/{orderNumber}")
	public int finishDelivery(@PathParam("orderNumber") String orderNumber) {
		OrderClient oc = new OrderClient();
		EmailClient ec = new EmailClient();

		Order order = oc.retrieveOrder(orderNumber);
		if(order!=null) {
			if(order.getStatus()==2) {
				order.setStatus(3);
				oc.updateOrder(order);

				//Identificaria o Cliente pelo cpf usando order.getCpf(), mas conforme exemplo, estamos usando o padrão
				ec.callSendMailService("robertorr9@gmail.com", "robertodm112", "tbsouza@outlook.com");
				System.out.println("Pedido "+orderNumber+" entregue !");
				return ResponseStatus.OK.ordinal();
			}

		}
		System.out.println("Pedido "+orderNumber+" não existe ou não pode ser finalizado !");
		return ResponseStatus.ERROR.ordinal();
	}

}