package ie.mohammed.model;

 
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapping entity to DB table users
 * 
 * @author Mohammed
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.IDENTITY) private int userId;
	 */
	@Id
	@Column(name = "username", nullable = false, unique = true, length = 50)
	private String userName;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * This field need to create user. Usual password filed is JsonIgnored.
	 * 
	 * @return
	 */
	@Transient
	private String set_pasword;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> roles;

	public User(String userName, String password, boolean enabled) {
		 this.userName = userName;		 
		 this.password = password;
		 this.enabled = enabled;
	}
}
