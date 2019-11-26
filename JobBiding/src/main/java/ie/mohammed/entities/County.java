package ie.mohammed.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// By Default SPring JPA converts camel notation into underscore notation 
// when mapping the logical names here to the physical
// names in the database. So here countyId would map to county_id in the database.
// But I wish to keep all the names the same so I have 

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class County {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int countyId;
	@Column(nullable=false, unique=true)
	private String countyName;
	
	@ManyToOne
	private MyUser addedBy; 
	
	@OneToMany(mappedBy="county", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JsonIgnore
    private List<Town> towns = new ArrayList<>();
	
	public County(String name) {
		this.countyName = name;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("County [countyId=" + countyId + ", countyName=" + countyName + "]");
		for(Town town: this.towns)
			s.append("\n\t" + town.getTownName());
		return s.toString();
	}

	public County(String countyName, MyUser addedBy) {
		this.countyName = countyName;
		this.addedBy = addedBy;
	}

	
}
