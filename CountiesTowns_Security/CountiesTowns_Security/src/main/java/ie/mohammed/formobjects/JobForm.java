package ie.mohammed.formobjects;

import java.time.LocalDate;

/**
 * This class is created for the form that collects
 * the data for a new county. 
 * There is only one piece of data to collect:
 * the name of the county. 
 * I make use of Lombok to create the boilerplate code. 
 */

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobForm {
	
	@Size(min=4, max=20)
	private String jobName;	
	
	@Size(min=4, max=20)
	private String jobDescription;
	
	private LocalDate todayDate;
	
	private int isActive;
}