package ie.mohammed.formobjects;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class maps to a form that collects the data
 * about a new town.
 * The data consists of the town's name and the county
 * to which it belongs. The HTML form has a dropdown menu 
 * from which the user selects a county. The ID of that
 * county is sent to the POST processor, hence the second
 * piece of data is not a Job object, but an integer.
 * Because the ID cannot be invalid, I have placed no
 * limitations on it.   
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidForm {
	
	//@Size(min=4, max=20)
	//private String bidName;
	
	private double bidAmount;
	
	private int jobId;
}