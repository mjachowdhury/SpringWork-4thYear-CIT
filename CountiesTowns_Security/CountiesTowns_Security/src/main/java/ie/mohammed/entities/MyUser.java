package ie.mohammed.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// Getter and Setter combined
@NoArgsConstructor
@Entity
public class MyUser {
	@Id
	@GeneratedValue
	int userId;

	private String firstname;
	private String lastname;

	@Column
	@NotNull
	@Email
	private String userEmail;

	@OneToMany(mappedBy="addedBy")
	List<Job> jobsCreatedByUser;
	
	@Column
	@Size(min=8)
	private String userPassword;

	// Based on a user having only one role. 
	@OneToOne
    @JoinColumn(name = "roleEmail", nullable = false)
	private Role userRole;

	@Column
	boolean userEnabled;
	
	public MyUser(@NotNull @Email String userEmail, @Size(min = 8) String userPassword, Role userRole, boolean userEnabled, String firstname, String lastname) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userEnabled = userEnabled;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userEnabled=" + userEnabled + "]";
	}
}
