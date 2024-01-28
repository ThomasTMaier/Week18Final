package burger.king.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import burger.king.controller.model.BurgerKingData;
import burger.king.controller.model.BurgerKingEmployee;
import burger.king.controller.model.BurgerKingInspectionRecords;
import burger.king.controller.model.BurgerKingTruckOrders;
import burger.king.dao.BurgerKingDao;
import burger.king.dao.EmployeeDao;
import burger.king.dao.InspectionRecordDao;
import burger.king.dao.TruckOrdersDao;
import burger.king.entitiy.BurgerKing;
import burger.king.entitiy.Employee;
import burger.king.entitiy.InspectionRecord;
import burger.king.entitiy.TruckOrders;
@Service
public class BurgerKingService {
	@Autowired 
	private BurgerKingDao bkDao;

	@Transactional(readOnly = false)
	public BurgerKingData saveBK(BurgerKingData bkData) {
		Long bkId = bkData.getBurgerKingId();
		BurgerKing bk = findOrCreateBK(bkId);
		copyBKFields(bk, bkData);
		return new BurgerKingData(bkDao.save(bk));
	}

	private void copyBKFields(BurgerKing bk, BurgerKingData bkData) {
		bk.setBurgerKingAddress(bkData.getBurgerKingAddress());
		bk.setBurgerKingCity(bkData.getBurgerKingCity());
		bk.setBurgerKingNumber(bkData.getBurgerKingNumber());
		bk.setBurgerKingZip(bkData.getBurgerKingZip());
		bk.setBurgerKingState(bkData.getBurgerKingState());
		
		
		
	}

	private BurgerKing findOrCreateBK(Long bkId) {
		BurgerKing bk;
		if(bkId == 0) {
			bk = new BurgerKing();
		}else {
			bk = findBKById(bkId);
		}
		return bk;
	}

	private BurgerKing findBKById(Long bkId) {
		return bkDao.findById(bkId)
				.orElseThrow(() -> new NoSuchElementException("no element found with id : " + bkId));
	}
@Transactional
	public BurgerKingData listBKById(Long bkId) {
		BurgerKing bk = findBKById(bkId);
		BurgerKingData bkData = new BurgerKingData(bk);
		return bkData;
	}

public Map<String, String> deleteBKById(Long burgerKingId) {
	BurgerKing bk = findBKById(burgerKingId);
	bkDao.delete(bk);
	Map<String, String> msg = new HashMap<String, String>();
	String m = ("Burger King to be Deleted : " + burgerKingId);
	msg.put(m, "Deleteion Successfull");
	return msg;
}
@Autowired
private EmployeeDao employeeDao;
@Transactional(readOnly = false)

public BurgerKingEmployee addEmployee(Long burgerKingId, BurgerKingEmployee burgerKingEmployee) {
	BurgerKing bk = findBKById(burgerKingId);
	Long employeeId = burgerKingEmployee.getEmployeeId();
	Employee emp = findOrCreateEmployee(burgerKingId, employeeId);
	copyEmployeeFields(emp, burgerKingEmployee);
	Set<BurgerKing> bks = new HashSet<>();
	bks.add(bk);
	emp.setBurgerKing(bks);
	Set<Employee> employees = new HashSet<>();
	employees.add(emp);
	bk.setEmployees(employees);
	Employee dbEmployee = employeeDao.save(emp);
	return new BurgerKingEmployee(dbEmployee);
}

private void copyEmployeeFields(Employee emp, BurgerKingEmployee burgerKingEmployee) {
	//.setBurgerKing(burgerKingEmployee.get);
	emp.setEmployeeEmail(burgerKingEmployee.getEmployeeEmail());
	emp.setEmployeeFirstName(burgerKingEmployee.getEmployeeFirstName());
	emp.setEmployeeLastName(burgerKingEmployee.getEmployeeLastName());
	emp.setEmployeeLevel(burgerKingEmployee.getEmployeeLevel());
	emp.setEmployeePhoneNumber(burgerKingEmployee.getEmployeePhoneNumber());
	
}

private Employee findOrCreateEmployee(Long burgerKingId, Long employeeId) {
	Employee emp;
	
	if(employeeId == 0) {
		emp = new Employee();
	}else {
		emp = findEmployeeById(burgerKingId, employeeId);
		
	}
	return emp;
}

private Employee findEmployeeById(Long burgerKingId, Long employeeId) {
	Employee emp = employeeDao.findById(employeeId)
			.orElseThrow(()-> new NoSuchElementException("no element found with Id : "+ employeeId));
	Set<BurgerKing> bks = new HashSet<>();
	BurgerKing bk = findBKById(burgerKingId);
	boolean isThere = false;
	for(int i = 0; i<bks.size(); i++) {
		
		if(bks.contains(bk)) {
			isThere = true;
		}
		
	}
	if(isThere) {
		return emp;
	}else {
		throw new IllegalArgumentException("Ids do not match");
	}

}
@Autowired
private InspectionRecordDao IRDao;
@Transactional(readOnly = false)
public BurgerKingInspectionRecords addInspectionRecord(Long burgerKingId, BurgerKingInspectionRecords burgerKingInspectionRecord) {
	BurgerKing bk = findBKById(burgerKingId);
	Long IRID = burgerKingInspectionRecord.getRecordId();
	InspectionRecord iR = findOrCreateIR(burgerKingId, IRID);
	copyIRFields(iR, burgerKingInspectionRecord );
	iR.setBurgerKing(bk);
	bk.getRecords().add(iR);
	InspectionRecord dbRecord = IRDao.save(iR);
	return new BurgerKingInspectionRecords(dbRecord);
}

private void copyIRFields(InspectionRecord iR, BurgerKingInspectionRecords burgerKingInspectionRecord) {
	iR.setBurgerKing(burgerKingInspectionRecord.getBurgerKing());
	iR.setPassOrFail(burgerKingInspectionRecord.getPassOrFail());
	iR.setPercentage(burgerKingInspectionRecord.getPercentage());
	iR.setRecordType(burgerKingInspectionRecord.getRecordType());
	
	
}

private InspectionRecord findOrCreateIR(Long burgerKingId, Long iRID) {
	InspectionRecord iR;
	if(iRID == 0) {
		iR = new InspectionRecord();
	}else {
		iR = findIRById(burgerKingId, iRID);
	}
	return iR;
}

private InspectionRecord findIRById(Long burgerKingId, Long iRID) {
	InspectionRecord iR = IRDao.findById(iRID)
			.orElseThrow(() -> new NoSuchElementException("no element found with id: " + iRID));
	if(burgerKingId == iR.getBurgerKing().getBurgerKingId()) {
		return iR;
	}else {
		throw new IllegalArgumentException("BurgerKing Id's do not match");
	}
}
@Autowired
private TruckOrdersDao TODao;
@Transactional

public BurgerKingTruckOrders addTruckOrder(Long burgerKingId, BurgerKingTruckOrders burgerKingTruckOrders) {
	BurgerKing bk = findBKById(burgerKingId);
	Long TOId = burgerKingTruckOrders.getOrderId();
	TruckOrders TO = findOrCreateTO(burgerKingId, TOId);
	copyTOFields(TO, burgerKingTruckOrders );
	TO.setBurgerKing(bk);
	bk.getOrders().add(TO);
	TruckOrders dbOrder = TODao.save(TO);
	return new BurgerKingTruckOrders(dbOrder);
}

private void copyTOFields(TruckOrders tO, BurgerKingTruckOrders burgerKingTruckOrders) {
	tO.setBurgerKing(burgerKingTruckOrders.getBurgerKing());
	tO.setDateDelivered(burgerKingTruckOrders.getDateDelivered());
	tO.setDateOrdered(burgerKingTruckOrders.getDateOrdered());
	tO.setOrderContents(burgerKingTruckOrders.getOrderContents());;
	
}

private TruckOrders findOrCreateTO(Long burgerKingId, Long tOId) {
	TruckOrders TO;
	
	if(tOId == 0) {
		TO = new TruckOrders();
	}else {
		TO = findTOById(burgerKingId, tOId);
	}
	return TO;
}

private TruckOrders findTOById(Long burgerKingId, Long tOId) {
	TruckOrders TO = TODao.findById(tOId)
			.orElseThrow(() -> new NoSuchElementException("no element found with id: " + tOId));

	if(burgerKingId == TO.getBurgerKing().getBurgerKingId()) {
		return TO;
	}else {
		throw new IllegalArgumentException("BurgerKing Ids do not match");
	}
	
}


}
