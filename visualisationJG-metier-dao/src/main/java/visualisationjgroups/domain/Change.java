package visualisationjgroups.domain;

public class Change{
	private String label;
	private String descrip;

	
	public Change(String label, String descrip) {
		super();
		this.label = label;
		this.descrip = descrip;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
};