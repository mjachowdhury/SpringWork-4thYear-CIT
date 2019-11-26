package ie.mohammed.formobjects;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserForm {
	
	@Size(min=4, max=20)
	private String registerUserFirstName;
	
	@Size(min=4, max=20)
	private String registerUserLastName;
	
	@Size(min=4, max=20)
	private String registerUserEmail;
	
	@Size(min=4, max=20)
	private String registerUserPassword;
	

}
