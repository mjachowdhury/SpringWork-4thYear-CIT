package ie.mohammed.formobjects;

import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobForm {
	@Size(min=4, max=30)
	private String jobName;
	
	public JobForm(String name) {
		this.jobName = name;
	}	
}
