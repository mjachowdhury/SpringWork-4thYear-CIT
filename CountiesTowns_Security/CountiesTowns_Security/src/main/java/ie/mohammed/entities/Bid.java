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
public class Bid {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bidId;

	/*
	 * @Column(nullable=false) private String bidName;
	 */
	
	@Column(nullable=false)
	private double bidAmount;
	
	@Column(nullable=false)
	//@ManyToOne
	private String userEmail; 
	
	//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
	@ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Job job;

	public Bid(double bidAmount, Job job, String userEmail) {
		this.bidAmount = bidAmount;
		this.job = job;
		this.userEmail = userEmail;
	}

	public Bid(double bidAmount, Job job) {
		this.bidAmount = bidAmount;
		this.job = job;
	}
	@Override
	public String toString() {
		return "Bid [bidAmount=" + bidAmount + ", userEmail=" + userEmail + ", job=" + job + "]";
	}

	 



}
