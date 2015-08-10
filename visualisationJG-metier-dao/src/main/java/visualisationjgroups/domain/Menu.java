package visualisationjgroups.domain;

import java.util.ArrayList;

public class Menu {

	private String label;
	private ArrayList<String> items = new ArrayList<String>();
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Menu(){
		items = new ArrayList<String>();
	};
	public Menu(String label, ArrayList<String> items) {
		
		this.label = label;
		this.items = items;
	}
	public ArrayList<String> getItems() {
		return items;
	}
	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
	
	
}
