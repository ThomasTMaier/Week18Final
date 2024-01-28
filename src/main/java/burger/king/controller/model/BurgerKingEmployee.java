package burger.king.controller.model;

import java.util.HashSet;
import java.util.Set;

import burger.king.entitiy.BurgerKing;
import burger.king.entitiy.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@NoArgsConstructor
public class BurgerKingEmployee {
	private long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeEmail;
	private String employeePhoneNumber;
	private String employeeLevel;
//	private Set<BurgerKingData> burgerKing = new HashSet<>();

	public BurgerKingEmployee(Employee employee) {
		this.employeeEmail = employee.getEmployeeEmail();
		this.employeeFirstName = employee.getEmployeeFirstName();
		this.employeeId = employee.getEmployeeId();
		this.employeeLastName = employee.getEmployeeLastName() ;
		this.employeeLevel = employee.getEmployeeLevel();
		this.employeePhoneNumber = employee.getEmployeePhoneNumber();
		

		
	}

}
