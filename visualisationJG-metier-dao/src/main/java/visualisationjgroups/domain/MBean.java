package visualisationjgroups.domain;

import java.util.ArrayList;

public class MBean {

	private String label;
	private ArrayList<Operation> listOper;
	private ArrayList<Attribute> listAtt;
	
	
	public MBean() {
		listOper = new ArrayList<Operation>();
		listAtt = new ArrayList<Attribute>();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public ArrayList<Operation> getListOper() {
		return listOper;
	}
	public void setListOper(ArrayList<Operation> listOper) {
		this.listOper = listOper;
	}
	public ArrayList<Attribute> getListAtt() {
		return listAtt;
	}
	public void setListAtt(ArrayList<Attribute> listAtt) {
		this.listAtt = listAtt;
	}
	
	
}
