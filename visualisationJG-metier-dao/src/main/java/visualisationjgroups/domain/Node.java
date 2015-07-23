package visualisationjgroups.domain;

import java.io.Serializable;
import java.util.ArrayList;


public class Node implements Serializable{
	
	/**
	 * membre du cluster
	 */
	private static final long serialVersionUID = 1L;
	     
		  private String logical_name;
		  
		  private String cluster_name;
		  private String physical_addr;
		  private String mcast_addr;
		  private String mcast_port;
		  private String bind_port = "0";
		  private String bind_addr;
		  private String external_addr = "_";
		  private String external_port = "_";
		  private String site = "";
		  // master ou non
		  private String master_site ;
		  // coordinateur ou non
		  private String coordinateur;
		  private String color;
		  // liste de membres
		  private ArrayList<String>view = new ArrayList<String>() ;
		  private ArrayList<String>viewMaster = new ArrayList<String>() ;
		//--------	 constructeur
		public Node() {
			coordinateur="false";
			color="#3182bd";
		}
		// ---------getters & setters
		  public String getSite() {
			return site;
		}
		public void setSite(String site) {
			this.site = site;
		}

		public String getMaster_site() {
			return master_site;
		}

		public void setMaster_site(String master_site) {
			this.master_site = master_site;
		}

		public ArrayList<String> getView() {
			return view;
		}

		public void setView(ArrayList<String> view) {
			this.view = view;
		}

		public String getMcast_addr() {
			return mcast_addr;
		}

		public void setMcast_addr(String mcast_addr) {
			this.mcast_addr = mcast_addr;
		}

		public String getMcast_port() {
			return mcast_port;
		}


		public void setMcast_port(String mcast_port) {
			this.mcast_port = mcast_port;
		}


		public String getBind_port() {
			return bind_port;
		}


		public void setBind_port(String bind_port) {
			this.bind_port = bind_port;
		}


		public String getExternal_addr() {
			return external_addr;
		}


		public void setExternal_addr(String external_addr) {
			this.external_addr = external_addr;
		}

		public String getExternal_port() {
			return external_port;
		}

		public void setExternal_port(String external_port) {
			this.external_port = external_port;
		}

		public String getLogical_name()
		  {
		    return this.logical_name;
		  }
		  
		  public void setLogical_name(String logical_name)
		  {
		    this.logical_name = logical_name;
		  }
		  
		  public String getCluster_name()
		  {
		    return this.cluster_name;
		  }
		  
		  public void setCluster_name(String cluster_name)
		  {
		    this.cluster_name = cluster_name;
		  }
		  
		  public String getPhysical_addr()
		  {
		    return this.physical_addr;
		  }
		  
		  public void setPhysical_addr(String physical_addr)
		  {
		    this.physical_addr = physical_addr;
		  }
		  public String getColor() {
				return color;
			}
			public void setColor(String color) {
				this.color = color;
			}
			public String getCoordinateur() {
				return coordinateur;
			}
			public void setCoordinateur(String coordinateur) {
				this.coordinateur = coordinateur;
			}
			
		//----------------------affichage 
		public  void display()
		  {
		    System.out.print(" \n--------------------------------\n"
		    		+ "logical_name  =  " + this.logical_name + "\n bind_addr =  " + this.bind_addr + 
		    		"\ncluster_name  =  " + this.cluster_name + "\n physical_addr  =  " + 
		    		this.physical_addr+ " \n bind_port = "+ this.bind_port +"\n mcast_addr = "+
		    		this.mcast_addr +"\n mcast_port = "+this.mcast_port  +"\n external_addr = "+
		    		this.external_addr +"\n external_port = "+this.external_port+"\n coordinateur = "
		    		+this.coordinateur+"\n view = ");
		    
		    if(view.size()>0)
		    {	
		       for(int i=0;i<view.size();i++)
		    		    System.out.print(view.get(i)+"\n     ");
		    		
		    }
		    		System.out.print(" \n--------------------------------\n");
		  }
		public ArrayList<String> getViewMaster() {
			return viewMaster;
		}
		public void setViewMaster(ArrayList<String> viewMaster) {
			this.viewMaster = viewMaster;
		}
		public String getBind_addr() {
			return bind_addr;
		}
		public void setBind_addr(String bind_addr) {
			this.bind_addr = bind_addr;
		}
		
		}
