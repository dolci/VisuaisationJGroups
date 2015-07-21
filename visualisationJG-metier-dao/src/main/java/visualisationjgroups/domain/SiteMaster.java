package visualisationjgroups.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class SiteMaster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nameSite;
	String nameMember;
	ArrayList<String> viewMaster = new ArrayList<String>();
	
	
	public SiteMaster() {
	
	}
	
	public SiteMaster(String nameSite, String nameMember,
			ArrayList<String> viewMaster) {
		super();
		this.nameSite = nameSite;
		this.nameMember = nameMember;
		this.viewMaster = viewMaster;
	}

	public String getNameSite() {
		return nameSite;
	}
	public void setNameSite(String nameSite) {
		this.nameSite = nameSite;
	}
	public String getNameMember() {
		return nameMember;
	}
	public void setNameMember(String nameMember) {
		this.nameMember = nameMember;
	}
	public ArrayList<String> getViewMaster() {
		return viewMaster;
	}
	public void setViewMaster(ArrayList<String> viewMaster) {
		this.viewMaster = viewMaster;
	}
	
	
	

}
