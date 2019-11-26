package ie.mohammed.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Town {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int townId;

	@Column(nullable=false)
	private String townName;
	
	//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
	@ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private County county;

	public Town(String townName, County county) {
		this.townName = townName;
		this.county = county;
	}

	@Override
	public String toString() {
		return "Town [name=" + townName + ", county="+county+"]";
	}



}
