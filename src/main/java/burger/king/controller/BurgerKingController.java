package burger.king.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import burger.king.controller.model.BurgerKingData;
import burger.king.controller.model.BurgerKingEmployee;
import burger.king.controller.model.BurgerKingInspectionRecords;
import burger.king.controller.model.BurgerKingTruckOrders;
import burger.king.service.BurgerKingService;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/burger_king")
@Slf4j
public class BurgerKingController {

	@Autowired
	private BurgerKingService burgerKingService;
	
	@PostMapping("/burger_king")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BurgerKingData addBurgerKing(@RequestBody BurgerKingData bkData) {
		log.info("Creating a Burger King resturant {}", bkData);
		burgerKingService.saveBK(bkData);
		return bkData;
	}
	@PutMapping("/burger_king/{burgerKingId}")
	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
	public BurgerKingData updateBurgerKing(@PathVariable Long burgerKingId, @RequestBody BurgerKingData burgerKingdata) {
		log.info("updating burger king {}", burgerKingId);
		return burgerKingService.saveBK(burgerKingdata);
	}
	@GetMapping("/burger_king/{burgerKingId}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public BurgerKingData listBK(@PathVariable Long burgerKingId) {
		log.info("listing a burger king {}", burgerKingId);
		return burgerKingService.listBKById(burgerKingId);
	}
	@DeleteMapping("/burger_king/{burgerKingId}")
	@ResponseStatus(code = HttpStatus.GONE)
	public Map<String, String> deleteBurgerKing(@PathVariable Long burgerKingId){
		log.info("Deleting burger king with id {}", burgerKingId);
		return burgerKingService.deleteBKById(burgerKingId);
	}
	//*****************************************Everything north of here is CRUD for just the Burger King Store entity**********************************

	@PostMapping("/burger_king/{burgerKingId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BurgerKingEmployee addEmployee(@PathVariable Long burgerKingId, @RequestBody BurgerKingEmployee burgerKingEmployee) {
		log.info("Adding an employee {}", burgerKingEmployee);
		return burgerKingService.addEmployee(burgerKingId, burgerKingEmployee);
		}
//	@PutMapping("/burger_king/employee/{employeeId}/{burgerKingId}")
//	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
//	public BurgerKingEmployee updateEmployee(@PathVariable Long burgerKingId, @RequestBody BurgerKingEmployee burgerKingEmployee, Long employeeId) {
//		log.info("Updating an employee {}", employeeId);
//		return burgerKingService.addEmployee(burgerKingId, burgerKingEmployee);
//	}
	@PostMapping("/burger_king/{burgerKingId}/records")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BurgerKingInspectionRecords addRecord(@PathVariable Long burgerKingId, @RequestBody BurgerKingInspectionRecords burgerKingInspectionRecords) {
		log.info("Adding an Inspection Record {}", burgerKingInspectionRecords);
		return  burgerKingService.addInspectionRecord(burgerKingId, burgerKingInspectionRecords);
	}
	@PostMapping("/burger_king/{burgerKingId}/orders")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BurgerKingTruckOrders addOrder (@PathVariable Long burgerKingId, @RequestBody BurgerKingTruckOrders burgerKingTruckOrders) {
		log.info("Adding a truck order {}",  burgerKingTruckOrders);
		return burgerKingService.addTruckOrder(burgerKingId, burgerKingTruckOrders);
	}
}
