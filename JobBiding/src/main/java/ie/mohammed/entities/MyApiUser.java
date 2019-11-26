package ie.mohammed.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class MyApiUser {
	@Id
	@GeneratedValue
	int userId;

	@Column
	@NotNull
	@Email
	String userEmail;

	@Column
	@Size(min=8)
	String userPassword;

	@OneToOne
    @JoinColumn(name = "roleEmail", nullable = false)
	Role userRole;

	@Column
	boolean userEnabled;
	
	public MyApiUser(@NotNull @Email String userEmail, @Size(min = 8) String userPassword, Role userRole, boolean userEnabled) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userEnabled = userEnabled;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userEnabled=" + userEnabled + "]";
	}
}
