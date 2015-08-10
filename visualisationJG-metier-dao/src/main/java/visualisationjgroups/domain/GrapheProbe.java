package visualisationjgroups.domain;

import java.util.ArrayList;

public class GrapheProbe {
	
	private ArrayList<Node> listNode;
	private ArrayList<Link> listLink;
	
	
	public GrapheProbe() {
		listNode = new ArrayList<Node>();
		listLink = new ArrayList<Link>();
		
	}
	public ArrayList<Node> getListNode() {
		return listNode;
	}
	public void setListNode(ArrayList<Node> listNode) {
		this.listNode = listNode;
	}
	public ArrayList<Link> getListLink() {
		return listLink;
	}
	public void setListLink(ArrayList<Link> listLink) {
		this.listLink = listLink;
	}
    
	
}
