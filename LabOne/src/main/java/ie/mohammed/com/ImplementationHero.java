package ie.mohammed.com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImplementationHero implements Hero{
	
	public String heroName;
	public Power heroPower;
	
	@Override
	public String toString() {
		return "ImplementationHero [heroName=" + heroName + ", heroPower=" + heroPower + "]";
	}
	
	
	

}
