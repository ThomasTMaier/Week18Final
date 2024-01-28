package burger.king.controller.model;

import burger.king.entitiy.BurgerKing;
import burger.king.entitiy.InspectionRecord;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class BurgerKingInspectionRecords {
	private long recordId;
	private String recordType;
	private String passOrFail;
	private String percentage;
	private BurgerKing burgerKing;

	public BurgerKingInspectionRecords(InspectionRecord iRecords) {
		this.recordId = iRecords.getRecordId();
		this.burgerKing = iRecords.getBurgerKing();
		this.passOrFail = iRecords.getPassOrFail();
		this.percentage = iRecords.getPercentage();
		this.recordType = iRecords.getRecordType();
	}

}
