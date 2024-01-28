package burger.king.controller.model;

import java.util.HashSet;
import java.util.Set;

import burger.king.entitiy.BurgerKing;
import burger.king.entitiy.Employee;
import burger.king.entitiy.InspectionRecord;
import burger.king.entitiy.TruckOrders;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class BurgerKingData {


	private	long burgerKingId;
	private String burgerKingAddress;
	private String burgerKingCity;
	private String burgerKingState;
	private int burgerKingZip;
	private String burgerKingNumber;
	
	private Set<BurgerKingEmployee> employees = new HashSet<>();
	private Set<BurgerKingTruckOrders> orders= new HashSet<>();
	private Set<BurgerKingInspectionRecords> records= new HashSet<>();
	public BurgerKingData(BurgerKing burgerKing) {
		this.burgerKingAddress = burgerKing.getBurgerKingAddress();
		this.burgerKingCity = burgerKing.getBurgerKingCity();
		this.burgerKingId = burgerKing.getBurgerKingId();
		this.burgerKingNumber = burgerKing.getBurgerKingNumber();
		this.burgerKingState = burgerKing.getBurgerKingState();
		this.burgerKingZip = burgerKing.getBurgerKingZip();
		
		for(Employee employee : burgerKing.getEmployees()) {
			
			this.employees.add(new BurgerKingEmployee(employee));
		}
		for(TruckOrders tOrders : burgerKing.getOrders()) {
			this.orders.add(new BurgerKingTruckOrders(tOrders));
		}
		for(InspectionRecord iRecords: burgerKing.getRecords()) {
			this.records.add(new BurgerKingInspectionRecords(iRecords));
		}
	}
}
