package ie.mohammed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mohammed Mapping entity to DB table "jobs"
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	/**
	 * This field need to represent only "category_name" property instead full
	 * category object
	 * 
	 * @return
	 */
	@Transient
	private String category_name;

	public String getCategory_name() {
		if (category_name == null) {
			category_name = category.getName();
		}
		return category_name;
	}

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "description")
	private String description;

}
