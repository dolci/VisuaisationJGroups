package visualisationjgroups.domain;

import java.util.ArrayList;

public class MbeanShow {
	
	private String name;
	private boolean checked = true;
	private boolean show = false;
	private ArrayList<MbeanShow> children = new ArrayList<MbeanShow> ();
	
	public MbeanShow(String name) {
		this.name = name;
		//children = new ArrayList<MbeanShow> ();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public ArrayList<MbeanShow> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<MbeanShow> children) {
		this.children = children;
	}
	
	

}
