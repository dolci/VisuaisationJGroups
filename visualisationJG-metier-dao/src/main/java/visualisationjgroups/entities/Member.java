package visualisationjgroups.entities;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name = "member")
public class Member implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Id_Member")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMember;
	
	@Column(name="logical_Name")
	private String logicalName;
	
	@Column(name="cluster_Name")
	private String clusterName;
	
	@Column (name="mcast_Addr")
	private String mcastAddr;
	
	@Column (name="mcast_Port")
	private String mcastPort;
	
	@Column(name="site")
	private String site;
	
	@Column (name="physical_Addr")
	private String physicalAddr;
	
	@Column (name="bind_Port")
	private String bindPort ;
	
	@Column (name="bind_Addr")
	private String bindAddr;
	
	@Column (name="external_Addr")
	private String externalAddr ;
	
	@Column (name="external_Port")
	 private String externalPort ;
	
	 @Column(name="view_List")
	 private String viewList;	
	 
	 @Column(name="view_Master_List")
	 private String viewMasterList;	
	 
	 @Column(name="status")
	 private String status;
	 
	 @Column(name="coordinateur")
	 private String coordinateur;
	 
	 /*@Column
	 private String color;
	 */
	

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "ID_GRAPHE") 
	 private Graphe graphe;
	   
	 
	
	/* public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}*/
	public String getMcastAddr() {
		return mcastAddr;
	}


	public String getViewMasterList() {
		return viewMasterList;
	}

	public void setViewMasterList(String viewMasterList) {
		this.viewMasterList = viewMasterList;
	}

	public void setMcastAddr(String mcastAddr) {
		this.mcastAddr = mcastAddr;
	}


	public String getMcastPort() {
		return mcastPort;
	}


	public void setMcastPort(String mcastPort) {
		this.mcastPort = mcastPort;
	}


	public String getCoordinateur() {
		return coordinateur;
	}

	public void setCoordinateur(String coordinateur) {
		this.coordinateur = coordinateur;
	}

	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public String getLogicalName() {
		return logicalName;
	}

	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getPhysical_addr() {
		return physicalAddr;
	}

	public void setPhysical_addr(String physical_addr) {
		this.physicalAddr = physical_addr;
	}

	public String getBind_port() {
		return bindPort;
	}

	public void setBind_port(String bind_port) {
		this.bindPort = bind_port;
	}

	public String getBind_addr() {
		return bindAddr;
	}

	public void setBind_addr(String bind_addr) {
		this.bindAddr = bind_addr;
	}

	public String getExternal_addr() {
		return externalAddr;
	}

	public void setExternal_addr(String external_addr) {
		this.externalAddr = external_addr;
	}

	public String getExternal_port() {
		return externalPort;
	}

	public void setExternal_port(String external_port) {
		this.externalPort = external_port;
	}

	public String getViewList() {
		return viewList;
	}

	public void setViewList(String viewList) {
		this.viewList = viewList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Graphe getGraphe() {
		return graphe;
	}

	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}

   public Member(){}
	public Member(String logicalName, String clusterName, String mcastAddr,
			String mcastPort, String site, String physicalAddr,
			String bindPort, String bindAddr, String externalAddr,
			String externalPort, String viewList, String viewMasterList,
			String status, String coordinateur, Graphe graphe) {
		super();
		this.logicalName = logicalName;
		this.clusterName = clusterName;
		this.mcastAddr = mcastAddr;
		this.mcastPort = mcastPort;
		this.site = site;
		this.physicalAddr = physicalAddr;
		this.bindPort = bindPort;
		this.bindAddr = bindAddr;
		this.externalAddr = externalAddr;
		this.externalPort = externalPort;
		this.viewList = viewList;
		this.viewMasterList = viewMasterList;
		this.status = status;
		this.coordinateur = coordinateur;
		this.graphe = graphe;
	}

	/*@Override
	public String toString() {
		return "Member [idMember=" + idMember + ", logicalName=" + logicalName
				+ ", clusterName=" + clusterName + ", mcastAddr=" + mcastAddr
				+ ", mcastPort=" + mcastPort + ", site=" + site
				+ ", physical_addr=" + physicalAddr + ", bind_port="
				+ bindPort + ", bind_addr=" + bindAddr + ", external_addr="
				+ externalAddr + ", external_port=" + externalPort
				+ ", viewList=" + viewList + ", status=" + status + ", graphe="
				+ graphe + "]";
	}*/


	
	
	

}
