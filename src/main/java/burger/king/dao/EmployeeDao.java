package burger.king.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import burger.king.entitiy.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
