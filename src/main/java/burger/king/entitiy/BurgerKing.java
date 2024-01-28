package burger.king.entitiy;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
public class BurgerKing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	long burgerKingId;
	private String burgerKingAddress;
	private String burgerKingCity;
	private String burgerKingState;
	private int burgerKingZip;
	@Column(unique=true)
	private String burgerKingNumber;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "burger_king_employee", joinColumns =
	@JoinColumn(name = "burger_king_id"), inverseJoinColumns =
	@JoinColumn(name = "employee_id"))
	private Set<Employee> employees = new HashSet<>();
	
	
	@OneToMany(mappedBy = "burgerKing", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<TruckOrders> orders= new HashSet<>();
	@OneToMany(mappedBy = "burgerKing", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<InspectionRecord> records= new HashSet<>();
}
