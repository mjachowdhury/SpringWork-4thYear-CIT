package ie.mohammed.com;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FranchiseImplementation implements Franchise {

	public List<Hero> franchiseHeros;
	public String franchiseName;
	
	 
	public void display() {
		System.out.println("JUSTICE LEAGUE");
		System.out.println("==============\n");
		franchiseHeros.forEach( n -> {
			System.out.println(n);
		} );		
	}

 
}
