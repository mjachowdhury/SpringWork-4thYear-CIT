package ie.mohammed.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mohammed Mapping entity to DB table "auctions"
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auctions")
public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "owner")
	private User owner;

	/**
	 * This field need to represent only "owner_name" property instead full user
	 * object
	 * 
	 * @return
	 */
	@Transient
	private String owner_name;

	public String getOwner_name() {
		if (owner_name == null) {
			owner_name = owner.getUserName();
		}
		return owner_name;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	private Job job;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", nullable = false)
	private Date endTime;

	@Column(name = "description", nullable = false)
	private String descripton;

	@Column(name = "finished", nullable = false)
	private Boolean finished;

}
