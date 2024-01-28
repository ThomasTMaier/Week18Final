package burger.king.entitiy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
public class InspectionRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recordId;
	private String recordType;
	private String passOrFail;
	private String percentage;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="burger_king_id")
	private BurgerKing burgerKing;
	
}
