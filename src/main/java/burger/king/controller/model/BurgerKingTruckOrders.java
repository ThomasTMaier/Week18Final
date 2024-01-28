package burger.king.controller.model;

import burger.king.entitiy.BurgerKing;
import burger.king.entitiy.TruckOrders;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data 
@NoArgsConstructor
public class BurgerKingTruckOrders {
	private long orderId;
	private String orderContents;
	private String dateDelivered;
	private String dateOrdered;

	private BurgerKing burgerKing;
	public BurgerKingTruckOrders(TruckOrders tOrders) {
		this.orderId = tOrders.getOrderId();
		this.orderContents = tOrders.getOrderContents();
		this.dateDelivered = tOrders.getDateDelivered();
		this.dateDelivered = tOrders.getDateOrdered();
	}

}
