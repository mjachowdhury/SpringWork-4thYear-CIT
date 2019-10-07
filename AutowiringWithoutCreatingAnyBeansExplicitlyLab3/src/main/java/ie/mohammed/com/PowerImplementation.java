package ie.mohammed.com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PowerImplementation implements Power {
	
	@Value("Iron Suit")
	public String powerName;
	@Value("Shoots lasers, speaks with Irish accent")
	public String powerDescription;
	
	
	@Override
	public String toString() {
		return "\n Power :" + powerName + "------>" + powerDescription;
	}
 
}
