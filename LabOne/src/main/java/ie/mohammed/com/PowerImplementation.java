package ie.mohammed.com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PowerImplementation implements Power {
	public String powerName;
	public String powerDescription;
	
	
	@Override
	public String toString() {
		return "\n Power :" + powerName + "------>" + powerDescription;
	}
 
}
