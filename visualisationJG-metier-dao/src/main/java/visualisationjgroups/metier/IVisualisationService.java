package visualisationjgroups.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import visualisationjgroups.domain.Node;
import visualisationjgroups.entities.Member;
import visualisationjgroups.entities.JmxPort;
import visualisationjgroups.entities.Graphe;
import visualisationjgroups.entities.Changement;



public interface IVisualisationService {
	
	   // list of member 
		public List<Member> getAllMemberDB(long idGraphe);
		// list of change by graph
		public List <Changement> getAllChangementByIdGraphe(long idGrpahe);
		// list of change between to date
		public List <Changement> getChangementByIdGrapheBetweeDate(long idGrpahe,Date from, Date to);
		//
		public List <Changement> getChangementByIdGrapheBetweeDateHeure(long idGrpahe,Date date, String from,String to);
		public List<Changement> findByDateheureModifBetween (long idGraphe,Date datefrom,Date dateTo, String heureFrom,String heureTo);
		//
		public List<Graphe> getGrapheByDateCreation(Date id);
		
	
		public Member findMemberByIdGrapheAndlogicalName(long idgraphe,String logicalName);
		
		public List<Member>getAllMemberActif(long idGrpahe);
		
		public List<Member>getAllMember(long idGrpahe);
		
		public Graphe findBydescription(String description );
		// get jmxport by id
		public JmxPort getJmxPortById(String id);		
		//
		public JmxPort addJmxPort(JmxPort jmxPort);
		//
		public Graphe addGraphe(Graphe grpahe);		
		//
		public Member addMember(Member member);
		//
		public Changement addChangement(Changement changement);
		
		
		
		/* business layer */
		//get all members JGroups cluster
		public  ArrayList <Node> getAllMembers();
		// create a graph
		/**
		 *  create topology
		 * @param nodes
		 * @return
		 */
		public TreeMap<String,ArrayList> createGraph(ArrayList<Node> members);
		/**
		 * 
		 * @param dateFrom
		 * @param dateTo
		 * @param heureFrom
		 * @param heureTo
		 * @return
		 */
		public TreeMap<String,Object>getGrapheAtDateWithAllChange(Date dateFrom,Date dateTo,String heureFrom,String heureTo);
		// list of coordinator 
		public ArrayList<Node> getAllCordinateurProbe(ArrayList<Node> members);
		//
		/**
		 * 
		 * @param nodess
		 * @return
		 */
		public TreeMap<String,ArrayList<String>> getMenu(ArrayList<Node> nodess);
		
		// invoke method via probe
		/**
		 * 
		 * @param nameProtocol 
		 * @param nameMethod
		 * @param addr ( unicast / multicast)
		 * @return
		 */
	    public TreeMap<String,List<String>> invokeMethodProbe(String nameProtocol,String nameMethod,String addr);
	    // value attribute via probe
	    /**
	     * 
	     * @param addresseIP
	     * @param params list of protocol and attribute
	     * @return
	     */
	    public List<String> readAttributeProbe(String addresseIP,TreeMap<String,List<String>> params);
	    // set attribute
	    /**
	     * 
	     * @param addresseIP
	     * @param params name of attribute
	     * @param value  value to set 
	     * @param nameProtocol 
	     * @return
	     */
	    public String setAttributeProbe(String addresseIP,String params,String value,String nameProtocol);
	    // add protocol
	    /**
	     * 
	     * @param addr unicast / multicast addresse
	     * @param name of insert protocol 
	     * @param transportP (UDP / TCP)
	     * @return
	     */
	    public String  addProtocol(String addr,String name,String transportP);
	    // delete protocol
	    /**
	     * 
	     * @param addr IP address
	     * @param name of protocol to remove
	     * @return
	     */
	    public String removeProtocol(String addr,String name);
	    //print stack protocol
	    public String printStackProtocol();
	    
	    public void scheduledHistory();
	   // list MBean 
	    /**
	     * 
	     * @param 
	     * @return
	     */
	 		public  TreeMap<String , Object> getAllMBean(String uuid,String addr);
	    // invoke method via JMX
	    public List<String> invokeMethodJMX();
}
