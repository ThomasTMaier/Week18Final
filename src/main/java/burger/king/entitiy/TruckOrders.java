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
public class TruckOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	private String orderContents;
	private String dateDelivered;
	private String dateOrdered;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="burger_king_id")
	private BurgerKing burgerKing;
}
