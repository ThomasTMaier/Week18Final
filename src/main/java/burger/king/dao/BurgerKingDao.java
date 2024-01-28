package burger.king.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import burger.king.entitiy.BurgerKing;

public interface BurgerKingDao extends JpaRepository<BurgerKing, Long> {

}
