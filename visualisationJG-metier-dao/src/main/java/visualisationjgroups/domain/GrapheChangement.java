package visualisationjgroups.domain;

import java.util.ArrayList;

public class GrapheChangement {

	private GrapheProbe graphe;
	
	private ArrayList<Change> listChange;
	
	
	public GrapheChangement() {
		listChange = new ArrayList<Change>();
	
	}
	public GrapheProbe getGraphe() {
		return graphe;
	}
	public void setGraphe(GrapheProbe graphe) {
		this.graphe = graphe;
	}
	public ArrayList<Change> getListChange() {
		return listChange;
	}
	public void setListChange(ArrayList<Change> listChange) {
		this.listChange = listChange;
	}
	
	
}
