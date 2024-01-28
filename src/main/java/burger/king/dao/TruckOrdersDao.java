package burger.king.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import burger.king.entitiy.TruckOrders;

public interface TruckOrdersDao extends JpaRepository<TruckOrders, Long> {

}
