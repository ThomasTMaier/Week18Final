package burger.king.entitiy;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
public class Employee {
	//Many to many, as at burger king you can work at many stores in a radius of your base store
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	private long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	@Column(unique=true)
	private String employeeEmail;
	@Column(unique=true)
	private String employeePhoneNumber;
	private String employeeLevel;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy="employees", cascade = CascadeType.PERSIST)
	private Set<BurgerKing> burgerKing = new HashSet<>();
}
