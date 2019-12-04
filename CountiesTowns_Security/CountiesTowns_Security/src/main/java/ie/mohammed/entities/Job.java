package ie.mohammed.entities;


import java.time.LocalDate;
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
import javax.swing.JPopupMenu;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// By Default SPring JPA converts camel notation into underscore notation 
// when mapping the logical names here to the physical
// names in the database. So here jobId would map to county_id in the database.
// But I wish to keep all the names the same so I have 

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int jobId;
	
	@Column(nullable=false, unique=true)
	private String jobName;
	
	@Column(nullable=false)
	private String jobDescription;
	
	@Column
	private LocalDate localeDate;
	
	@Column(nullable=false)
	private int isActiveJob;
	
	
	@ManyToOne
	private MyUser addedBy; 
	
	@OneToMany(mappedBy="job", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JsonIgnore
    private List<Bid> bids = new ArrayList<>();
	
	
	public Job(String jobName, String jobDescription, LocalDate localeDate, int isActiveJob, MyUser addedBy) {
		//super();
		this.jobName = jobName;
		this.jobDescription = jobDescription;
		this.localeDate = localeDate;
		this.isActiveJob = isActiveJob;
		this.addedBy = addedBy;
	}	 

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Job [jobId=" + jobId + ", jobName=" + jobName + ", jobDescription=" + jobDescription +  "]");
		for(Bid bid: this.bids) s.append("\n\t" + bid.getBidAmount()); 
		return s.toString();
	}

	 

	

	
}
