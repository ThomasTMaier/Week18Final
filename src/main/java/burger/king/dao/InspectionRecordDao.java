package burger.king.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import burger.king.entitiy.InspectionRecord;

public interface InspectionRecordDao extends JpaRepository<InspectionRecord, Long> {

}
