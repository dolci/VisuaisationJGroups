package visualisationjgroups.entities;




import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name = "changement")
public class Changement  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="description")
	private String description;
	
	 @Column(name="date_Modif")
	 @Temporal(TemporalType.DATE)
	 private Date dateModif;	
	 
	 @Column(name = "heure_Modif")
	 private String heureModif;
		
	 @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	 @NotFound(action = NotFoundAction.IGNORE)
	 @JoinColumn(name = "ID_GRAPHE") 
	 private Graphe graphe;
	   
	 
	 @PrePersist
	   protected void onCreate() {
		dateModif = new Date();
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		heureModif = localDateFormat.format(new Date());
	   }
	
	public Changement() {	
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDiscription() {
		return description;
	}


	public void setDiscription(String discription) {
		this.description = discription;
	}


	public Date getDateModif() {
		return dateModif;
	}


	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}


	public String getHeureCreation() {
		return heureModif;
	}

	public void setHeureCreation(String heureCreation) {
		this.heureModif = heureCreation;
	}

	public Graphe getGraphe() {
		return graphe;
	}


	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}
    
	
	
	

}
