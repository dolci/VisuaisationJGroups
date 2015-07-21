package visualisationjgroups.domain;
/**
 * lien entre noeud de graphe
 * 
 *
 */
public class Link  {
	//source 
  private String link;
  // id de source
  private int source;
  //id de destination
  private int target;
  
 // ------getters & setters
public String getLink() {
	return link;
}


public void setLink(String link) {
	this.link = link;
}


public int getSource() {
	return source;
}


public void setSource(int source) {
	this.source = source;
}


public int getTarget() {
	return target;
}


public void setTarget(int target) {
	this.target = target;
}


public Link() {
	super();
	// TODO Auto-generated constructor stub
}
  
}
