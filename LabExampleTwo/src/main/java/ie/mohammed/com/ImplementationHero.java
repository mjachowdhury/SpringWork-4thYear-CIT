package ie.mohammed.com;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ImplementationHero implements Hero{
	
	public String heroName;
	public String heroPower;
	
	public ImplementationHero() {}
	public ImplementationHero(String heroName, String heroPower) {
		super();
		this.heroName = heroName;
		this.heroPower = heroPower;
	}
	public String getHeroName() {
		return heroName;
	}
	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	public String getHeroPower() {
		return heroPower;
	}
	public void setHeroPower(String heroPower) {
		this.heroPower = heroPower;
	}
	@Override
	public String toString() {
		return "ImplementationHero [heroName=" + heroName + ", heroPower=" + heroPower + "]";
	}
	
	

}
