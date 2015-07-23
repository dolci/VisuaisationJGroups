package visualisationjgroups.entities;



import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;










import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "graphe")
public class Graphe implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name="description")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_Creation")
    private Date dateCreation;
	
	@Column(name="heure_Creation")
	private String heureCreation;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="graphe",cascade=CascadeType.ALL)
    private Set<Member> listMember  ;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="graphe",cascade=CascadeType.ALL)
    private Set<Changement> listChangement  ;
	
	@PrePersist
	   protected void onCreate() {
		dateCreation = new Date();
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		heureCreation = localDateFormat.format(new Date());
	   }
	
	public String getHeureCreation() {
		return heureCreation;
	}

	public Graphe(String description) {
		
		this.description = description;
		listMember = new HashSet<Member>();
		listChangement = new HashSet<Changement>();
	}

	public void setHeureCreation(String heureCreation) {
		this.heureCreation = heureCreation;
	}

	public Graphe() {	
		listMember = new HashSet<Member>();
		listChangement = new HashSet<Changement>();
	}
    


	public Set<Changement> getListChangement() {
		return listChangement;
	}

	public void setListChangement(Set<Changement> listChangement) {
		this.listChangement = listChangement;
	}

	public Collection<Member> getListMember() {
		return listMember;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setListMember(Set<Member> listMember) {
		this.listMember = listMember;
	}

	/*@Override
	public String toString() {
		return "Graphe [id=" + id + ", description=" + description
				+ ", dateCreation=" + dateCreation + ", listMember="
				+ listMember + "]";
	}*/


	
	
	

}
