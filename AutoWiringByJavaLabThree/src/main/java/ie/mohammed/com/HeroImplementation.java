package ie.mohammed.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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

public class HeroImplementation implements Hero{
	
	public String heroName;
	
	@Autowired
	public Power heroPower;

	public HeroImplementation(String heroName) {
		this.heroName = heroName;
	}

}
