package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.Orders;

public interface Logistics {

	Orders startDelivery();

	int finishDelivery(String orderNumber);

}