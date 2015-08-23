package visualisationjgroups.domain;

import java.util.ArrayList;
import java.util.List;

public class RepMethod {

	private String addr;
	private List<String> rep;
	private ArrayList<RepMethodProbe> listProbe ;
	
	public RepMethod(){
	     rep = new  ArrayList<String>();
	     listProbe = new ArrayList<RepMethodProbe>();
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public List<String> getRep() {
		return rep;
	}
	public void setRep(List<String> rep) {
		this.rep = rep;
	}
	public ArrayList<RepMethodProbe> getListProbe() {
		return listProbe;
	}
	public void setListProbe(ArrayList<RepMethodProbe> listProbe) {
		this.listProbe = listProbe;
	}
	
}
