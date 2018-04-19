package br.inatel.dm112.client;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.Orders;
import br.inatel.dm112.model.ResponseStatus;

public class LogisticsTest {

	//TODO: Fique à vontade para alterar estes atributos
	//TODO: Para enviar um email através do gmail, é necessário habilitar o SMTP da conta de envio.
	private String sendToAddress = "tbsouza@outlook.com";
	private String sendFromAddress = "robertorr9@gmail.com";
	private String sendPassAddress = "robertodm112";

	public Orders startDelivery() {//return pendentList?
		OrderClient oc = new OrderClient();
		
		return oc.getPendentOrders();
	}
	
	public int finishDelivery(String orderNumber) {
		OrderClient oc = new OrderClient();
		EmailClient ec = new EmailClient();
		
		Order order = oc.retrieveOrder(orderNumber);
		if(order!=null) {
				if(order.getStatus()==2) {
					order.setStatus(3);
					oc.updateOrder(order);
					
					//Identificaria o Cliente pelo cpf usando order.getCpf(), mas conforme exemplo, estamos usando o padrão
					ec.callSendMailService(sendFromAddress, sendPassAddress, sendToAddress);
					return ResponseStatus.OK.ordinal();
			}
		}
				
		return ResponseStatus.ERROR.ordinal();
	}

	public static void main(String[] args) {
		LogisticsTest lt = new LogisticsTest();
		lt.startDelivery();
		lt.finishDelivery("457");
	}


}
