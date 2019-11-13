package ie.mohammed.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Mohammed Mapping entity to DB table "bids"
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bids")
public class Bid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "auction_id")
	private Auction auction;

	/**
	 * This field need to represent only "auction_id" property instead full auction
	 * object
	 * 
	 * @return
	 */
	@Transient
	private int auction_id;

	public int getAuction_id() {
		if (auction_id == 0) {
			auction_id = auction.getId();
		}
		return auction_id;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	/**
	 * This field need to represent only "user_name" property instead full user
	 * object
	 * 
	 * @return
	 */
	@Transient
	private String user_name;

	public String getUser_name() {
		if (user_name == null) {
			user_name = user.getUserName();
		}
		return user_name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bet_time", nullable = false)
	private Date bidTime;

	@Column(name = "price", nullable = false)
	private Double price;

}
