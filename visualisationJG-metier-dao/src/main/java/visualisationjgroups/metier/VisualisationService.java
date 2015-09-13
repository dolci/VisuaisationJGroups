package visualisationjgroups.metier;


import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import visualisationjgroups.domain.Change;
import visualisationjgroups.domain.Color;




import visualisationjgroups.domain.GrapheChangement;
import visualisationjgroups.domain.GrapheProbe;
import visualisationjgroups.domain.HelperStructure;
import visualisationjgroups.domain.Link;
import visualisationjgroups.domain.MBean;
import visualisationjgroups.domain.MbeanShow;
import visualisationjgroups.domain.Menu;
import visualisationjgroups.domain.Node;
import visualisationjgroups.domain.Probe;
import visualisationjgroups.domain.RepMethod;
import visualisationjgroups.domain.RepMethodProbe;
import visualisationjgroups.entities.Member;
import visualisationjgroups.entities.JmxPort;
import visualisationjgroups.entities.Graphe;
import visualisationjgroups.entities.Changement;
import visualisationjgroups.metier.IVisualisationService;
import visualisationjgroups.repositories.GrapheRepository;
import visualisationjgroups.repositories.JmxPortRepository;
import visualisationjgroups.repositories.MemberRepository;
import visualisationjgroups.repositories.ChangementRepository;

@Service("metier")
public class VisualisationService  implements IVisualisationService{

		// repositories
		@Autowired
		private
		GrapheRepository grapheRepository;
		@Autowired 
		JmxPortRepository jmxPortRepository;
		@Autowired
		private MemberRepository memberRepository;
		@Autowired
		private ChangementRepository changementRepository;
		/* @Autowired
		  private Environment environment;*/
		
		private String changeGraphe;
		 
	    
		public JmxPort getJmxPortById(String id) {
			return jmxPortRepository.findOne(id);
		}
		
		public JmxPort addJmxPort(JmxPort jmxPort) {
			
			return jmxPortRepository.save(jmxPort);
		}
		
		public Graphe addCoordinateur(Graphe graphe) {
		
			return grapheRepository.save(graphe);
		}
		
		public Member addViews(Member member) {
			
			return memberRepository.save(member);
		}
		
		
		@Override
		/**
		 * 
		 */
		public ArrayList <Node> getAllMembers() {
			
			Probe probe = new Probe();
			int nbreRep = 0;
			int test = 0;
			try{				
				 probe.getQuery().add("jmx=RELAY2.site jmx=UDP.mcast_addr,mcast_port,bind_port,external_addr,external_port,bind_addr");
				 probe.start(InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR()), probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
						probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
				nbreRep = probe.getResponses().size();
				do{
					probe.setTimeout(probe.getTimeout()*2);
					probe.getQuery().add("jmx=RELAY2.site jmx=UDP.mcast_addr,mcast_port,bind_port,external_addr,external_port,bind_addr");
					probe.start(InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR()), probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
							probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
					if(nbreRep == probe.getResponses().size())
						test = 1;
					else
						nbreRep = probe.getResponses().size();
				} while(test ==1);
			}
			catch(Exception e){
				
			}
			return HelperStructure.structureRepMemberProbe(probe.getResponses());
		}
		
		@Override
		/**
		 * 
		 */
		public GrapheProbe createGraph(ArrayList<Node> members) {
			ArrayList<Node> nodeList = new ArrayList<Node>();
			if(getAllMemberDB(6).size()>0){
			for(Member m: getAllMemberDB(6)){
				 for(Node n : members){
					 if(m.getLogicalName().equals(n.getLogical_name())){
						 nodeList.add(n);
						 members.remove(n);
						 break;
						 }
				 }
				}
			nodeList.addAll(members);members.clear();
			members = nodeList;
			}
			TreeMap<String,String> colorList = new TreeMap<String,String>();
			int index = 0, size1 = members.size();
			//color group
			System.out.println("member****************** "+members.get(0));
			members.get(0).setColor(Color.getColor().get(0));
			colorList.put(members.get(0).getMcast_addr()+":"+members.get(0).getMcast_port(), Color.getColor().get(0));
			for(int i =1 ;i<size1;i++){
				String key = members.get(i).getMcast_addr()+":"+members.get(i).getMcast_port();
				 if(colorList.containsKey(key))
					 members.get(i).setColor(colorList.get(key));
				 else {
					 index++;
					 colorList.put(key, Color.getColor().get(index));
					 members.get(i).setColor(Color.getColor().get(index));
				 }
					 
			}
			
			// nodes
			for(Node n : members){
				if(n.getCoordinateur().equals("true"))
					n.setColor("#cc0000");
				if(n.getViewMaster().size()>0)
			    	n.getView().addAll(n.getViewMaster());
				//System.out.println("  views  "+HelperStructure.displayList(n.getView()));
			}
			
			
			//create link
			ArrayList <Link> links = new ArrayList<Link>();			
			int size = members.size();
		    for(int i = 0;i<size ;i++){
		    	if(members.get(i).getCoordinateur().equals("true")){
			    int sizeView = members.get(i).getView().size();
			    ArrayList<String> views = members.get(i).getView();
				for(int j=0; j<sizeView;j++){
						for(int k = 0 ; k<size; k++){
					     if( views.get(j).trim().equals(members.get(k).getLogical_name()) ){   
					    	 Link l = new Link();
							 l.setLink(members.get(i).getLogical_name());
						     l.setSource(i);
					    	 l.setTarget(k);
					    	 links.add(l);break;
					    	 }
						}
					}
				}
				
			}
		    GrapheProbe graphe = new GrapheProbe();
		    graphe.setListNode(members);
		   graphe.setListLink(links);
			return graphe;
		}
		
		@Override
		/**
		 * 
		 */
		public ArrayList<Node> getAllCordinateurProbe(ArrayList<Node> members) {
			ArrayList<Node> listCoord = new ArrayList<Node>();
             for(Node node : members){
            	 if(node.getCoordinateur().equals("true")){
            		  listCoord.add(node);
            	 }
            		 
             }
			return listCoord;
		}
		
		
		@Override
		/**
		 * 
		 */
		public ArrayList<Menu> getMenu(ArrayList<Node> nodess) {
			
			
			ArrayList<Node> nodeList = new ArrayList<Node>();
			if(getAllMemberDB(6).size()>0){
			for(Member m: getAllMemberDB(6)){
				 for(Node n : nodess){
					 if(m.getLogicalName().equals(n.getLogical_name())){
						 nodeList.add(n); nodess.remove(n);
						 break;
						 }
				 }
				}
			nodeList.addAll(nodess);
			nodess.clear();
			nodess = nodeList;
			}
			ArrayList<String> listItem = new ArrayList<String>();
			ArrayList<Menu> listMenu = new ArrayList<Menu>();
			Menu m  = new Menu();
			if(nodess.size()>0){
				m.setLabel(nodess.get(0).getMcast_addr()+":"+nodess.get(0).getMcast_port());
				listItem.add(nodess.get(0).getLogical_name());
				m.setItems(listItem);
				listMenu.add(m);
			
			for(int i = 1 ; i<nodess.size() ;i++){
				int index = HelperStructure.existElemDansList(listMenu,nodess.get(i).getMcast_addr()+":"+nodess.get(i).getMcast_port());
				if (index == -1){
					Menu m1  = new Menu();m1.setLabel(nodess.get(i).getMcast_addr()+":"+nodess.get(i).getMcast_port());
					ArrayList<String> listItem1 = new ArrayList<String>();
					listItem1.add(nodess.get(i).getLogical_name());
					m1.setItems(listItem1);
					listMenu.add(m1);
				}
				else{
					listMenu.get(index).getItems().add(nodess.get(i).getLogical_name());
				}
				
			}
			}
			
			
			return listMenu;
		}
		
		@Override
		/**
		 * invoke method via Probe
		 */
		public ArrayList<RepMethod> invokeMethodProbe(String nameProtocol,String nameMethod,String addresseIP) {
			
			InetAddress addr = null;
			Probe probe = new Probe();
			ArrayList<RepMethod> repList = new ArrayList<RepMethod>();
			probe.getQuery().add("op="+nameProtocol+"."+nameMethod);
			
			try{
			if(addresseIP.equals("All")){
			    addr = InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR());
			    probe.start(addr, probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
						probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
			    RepMethod rep = new RepMethod();
			    //System.out.println(rep.getListProbe().size());
			    rep.setAddr("All");rep.setListProbe(HelperStructure.stucturerProbe(probe.getResponses(), nameProtocol,0));
			    repList.add(rep);
			    }
			else{
			     System.out.println("adresse "+ addresseIP);

				 String [] logicalNames = addresseIP.split(",");
				 for(String logicalName :logicalNames){
					 RepMethod rep = new RepMethod();
					 for( Node n :getAllMembers()){
						 if(n.getLogical_name().equals(logicalName)){
							 RepMethodProbe repJMX = new  RepMethodProbe(); 
							 repJMX.setLogicalName(n.getBind_addr());
							 System.out.println("*******"+invokeMethodJMX( n.getBind_addr(), logicalName, nameProtocol, nameMethod));
							 repJMX.setReProtocol(invokeMethodJMX( n.getBind_addr(), logicalName, nameProtocol, nameMethod));
							 rep.getListProbe().add(repJMX);
							 break;
						 }
					 }
					
					 /*addr = InetAddress.getByName(addresseIP);
					 probe.start(addr, probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
								probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());*/
				//	 invokeMethodJMX( addr, logicalName, nameProtocol, nameMethod);
					 rep.setAddr(logicalName);
					// rep.setListProbe(HelperStructure.stucturerProbe(probe.getResponses(), nameProtocol));
					 repList.add(rep);
				 }
			}
								
			
			}
			catch(Exception e){
				
			}
			return repList;
		}
		
		@Override
		/**
		 *  read attribute  from probe
		 */
		public ArrayList<RepMethod> readAttributeProbe(String addresseIP,String protocol,String params) {
			
			String query = "";
			InetAddress addr ;
			RepMethod rep ;
			Probe probe = new Probe();
			ArrayList<RepMethod> repList = new ArrayList<RepMethod>();
			try{
				// command line protocol +attribute 
                  
					query+="jmx="+protocol+".";System.out.println(protocol);
					String [] attributes = params.split(",");
					for(int i=0 ;i<attributes.length;i++){
						if(i == attributes.length - 1)
							query+=attributes[i];
						else
							query+=attributes[i]+",";
					}
					
				probe.getQuery().add(query);
				
			if(addresseIP.equals("All")){
				addr = InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR());
				probe.start(addr, probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
						probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());		      
			    //System.out.println(rep.getListProbe().size());
			       rep = new RepMethod();
			    rep.setAddr("All");rep.setListProbe(HelperStructure.stucturerProbe(probe.getResponses(),protocol,1));
			    System.out.println("                          **** "+HelperStructure.stucturerProbe(probe.getResponses(),protocol,1));
			    repList.add(rep);
				
				}
			else{
			
				
				String [] adrs = addresseIP.split(",");
				for(String a : adrs){
					addr = InetAddress.getByName(a);
			        probe.start(addr, probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
					probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
			        rep = new RepMethod();
				    //System.out.println(rep.getListProbe().size());
				    rep.setAddr(a);rep.setListProbe(HelperStructure.stucturerProbe(probe.getResponses(),protocol,1));
				    repList.add(rep);
			
			
				}
			}
			}
			catch(Exception e){
				
			}
			
			return repList;
			
		}
		@Override
		/**
		 * set attribute on unicast or multicast
		 */
		public String setAttributeProbe(String addresseIP,String params,String value,String nameProtocol) {
			
			String res = "";
			Probe probe = new Probe();
			probe.getQuery().add("jmx="+nameProtocol+"."+params+"="+value);
			try{
			if(addresseIP.equals("all"))
			{
				
			    probe.start(InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR()), probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
					probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
				  	res = "ok";		
			}
			else{
				probe.start(InetAddress.getByName(addresseIP), probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
						probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
			}
			}
			catch(Exception e){
				res = "false";
			}
			return res;
		
		}
		
		
		@Override
		/**
		 * add a protocol at runtime
		 */
	    public String addProtocol(String addresseIP, String nameProtocol,String transportP,String position) {
			String res = "";
			InetAddress addr;
			Probe probe = new Probe();
			probe.getQuery().add("insert-protocol="+nameProtocol+"="+position+"="+transportP);
			System.out.println("insert pro "+"insert-protocol="+nameProtocol+"="+position+"="+transportP);
			try{
				if(addresseIP.equals("All"))
					   addr = InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR());
					else
						addr = InetAddress.getByName(addresseIP);
					probe.start(addr, probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
							probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
					res = "ok";
					}
					catch(Exception e){
						System.out.println(e.getMessage());
						res = "false";
					}
					return res;
		}
		@Override
		/**
		 * remove a protocol at runtime
		 */
        public String removeProtocol(String addresseIP, String nameProtocol) {
			InetAddress addr;
			String res = "";
			Probe probe = new Probe();
			try{
				probe.getQuery().add("remove-protocol="+nameProtocol);
			if(addresseIP.equals("All"))
			   addr = InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR());
			else
				addr = InetAddress.getByName(addresseIP);
			probe.start(addr, probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
					probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
			res = "ok";
			}
			catch(Exception e){
				res = "false";
			}
			return res;
		}
		@Override
		/**
		 * print stack protocol
		 */
		public String printStackProtocol() {
			Probe probe = new Probe();
			try{				
				probe.getQuery().add("print-protocols");
				 probe.start(InetAddress.getByName(probe.getDEFAULT_DIAG_ADDR()), probe.getBind_addr(), probe.getPort(), probe.getTtl(), probe.getTimeout(),
						probe.getQuery(),null, probe.isWeed_out_duplicates(), probe.getPasscode());
				 
			}
			catch(Exception e){
				
			}
			return  HelperStructure.structurePrintStackProtocol(probe.getResponses());
		}

		
		/**
		 * 
		 */
		public String invokeMethodJMX(String addr,String logicalName,String nameProtocol,String nameMethod) {
			 MBeanServerConnection server = null;
			 JMXConnector conn;
			 JMXServiceURL jmxservice; 
			 JmxPort jmxPort;
			String res = null;
			int port = 50000 /*Integer.parseInt(environment.getRequiredProperty("jmx.port"))*/,rep = 0;
			
			if (getJmxPortById(logicalName) == null){
				// determined port 
				 while(rep == 0){		 
					 
				         try {
				        	 //System.out.println("************************** "+addr);
					              jmxservice = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+addr+":"+port+"/jmxrmi");			    
					              conn = JMXConnectorFactory.connect(jmxservice);		
					              server = conn.getMBeanServerConnection();
					              if(HelperStructure.verifMBeanLogiclName(server,logicalName) == 1){
					                rep=1;
					                jmxPort =(JmxPort) new JmxPort().build(logicalName,0);jmxPort.setAddr(addr);jmxPort.setPort(port);
								    addJmxPort(jmxPort);
								   
								  }
				             } 	    
				      catch ( Exception t) {	/*t.printStackTrace();*/}
				      port++;
				 }
							 
			}
			else{
				  jmxPort = getJmxPortById(logicalName);
				 // System.out.println("jmpor************************** "+jmxPort);
				   try {
		              jmxservice = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+addr+":"+jmxPort.getPort()+"/jmxrmi");			    
		              conn = JMXConnectorFactory.connect(jmxservice);		 
		              server = conn.getMBeanServerConnection();
		              Set<ObjectInstance> mbeans = server.queryMBeans(null, null);
					  for (ObjectInstance objectIns : mbeans)  {
						  if(objectIns.getClassName().contains(nameProtocol)) {
							  ObjectName adaptorP = objectIns.getObjectName();
							  
							  res = server.invoke(adaptorP,nameMethod, null, null).toString();
						  }
					  }
		             
	                 } 	    
	               catch ( Exception t) {	/*t.printStackTrace();*/}
			}
			
			return res;
		}	
		@Override
		/**
		 *  get all mbea relatif of member 
		 */
		public MbeanShow getAllMBeanShow(String logicalName,String addr) {
			 MBeanServerConnection server = null;
			 JMXConnector conn;
			 JMXServiceURL jmxservice; 
			 JmxPort jmxPort;
			
			int port = 50000 /*Integer.parseInt(environment.getRequiredProperty("jmx.port"))*/,rep = 0;
			
			if (getJmxPortById(logicalName) == null){
				// determined port 
				 while(rep == 0){		 
					 
				         try {
				        	 //System.out.println("************************** "+addr);
					              jmxservice = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+addr+":"+port+"/jmxrmi");			    
					              conn = JMXConnectorFactory.connect(jmxservice);		
					              server = conn.getMBeanServerConnection();
					              if(HelperStructure.verifMBeanLogiclName(server,logicalName) == 1){
					                rep=1;
					                jmxPort =(JmxPort) new JmxPort().build(logicalName,0);jmxPort.setAddr(addr);jmxPort.setPort(port);
								    addJmxPort(jmxPort);
								   
								  }
				             } 	    
				      catch ( Exception t) {	/*t.printStackTrace();*/}
				      port++;
				 }
							 
			}
			else{
				  jmxPort = getJmxPortById(logicalName);
				 // System.out.println("jmpor************************** "+jmxPort);
				   try {
		              jmxservice = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+addr+":"+jmxPort.getPort()+"/jmxrmi");			    
		              conn = JMXConnectorFactory.connect(jmxservice);		 
		              server = conn.getMBeanServerConnection();
		             
	                 } 	    
	               catch ( Exception t) {	/*t.printStackTrace();*/}
			}
			
			
			return HelperStructure.returnAttributeOperationJGroups(server);
		}
	
		@Override
		public ArrayList<MBean> getAllMBean(String logicalName,String addr) {
			 MBeanServerConnection server = null;
			 JMXConnector conn;
			 JMXServiceURL jmxservice; 
			 JmxPort jmxPort;
			int port = 50000 /*Integer.parseInt(environment.getRequiredProperty("jmx.port"))*/,rep = 0;
			
			
				// determined port 
				 while(rep == 0){		 
				         try {
					              jmxservice = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+addr+":"+port+"/jmxrmi");			    
					              conn = JMXConnectorFactory.connect(jmxservice);		
					              server = conn.getMBeanServerConnection();
					              try {
						              jmxservice = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+addr+":"+port+"/jmxrmi");			    
						              conn = JMXConnectorFactory.connect(jmxservice);		 
						              server = conn.getMBeanServerConnection();
						              rep=1;
					                 } 	    
					               catch ( Exception t) {	/*t.printStackTrace();*/}
							           
					   } 	    
				      catch ( Exception t) {	/*t.printStackTrace();*/}
				      port++;
				 }
							 
			
			
				  
			
			return HelperStructure.returnAttributeOperationMBJGroups(server);
		}
		@Override
		public void scheduledHistory() throws Exception {
		 
			 Graphe graphe = findBydescription("initial");
		     List<Node> listNode =  getAllCordinateurProbe(getAllMembers());
		     Set<Member>  listMember = new  HashSet<Member>();
		     Set<Changement> listChange = new HashSet<Changement>();
		     if (listNode.size() != 0){
		    	 // initial :add all node to database
                  
		    	 if(graphe == null){
		    		// System.out.println("************* addd ***************");
		    		  Graphe grapheTmp = new Graphe("initial");
		    		 for(Node n :listNode ){
		    			 Member m = HelperStructure.convertNodeToMember(n);
		    			  m.setGraphe(grapheTmp);
		    			 listMember.add(m);
		    			
		    			 }	
		    		 grapheTmp.setListMember(listMember);
		    		 addGraphe(grapheTmp);
		        }
		    	 else{
		    		 
		    		 ArrayList<Member> listMemberDb = Lists.newArrayList(getAllMember(graphe.getId()));
		    		 for(Member m :listMemberDb ){
		    			 int i,b = -1;
		    			 for( i=0;i< listNode.size();i++)
		    				 if(m.getLogicalName().equals(listNode.get(i).getLogical_name())){
		    					b=1; break;
		    					 }
		    			 if( b == -1 && i==listNode.size()){
		    				if(m.getStatus().equals("actif")){
		    				 Changement change = new Changement();
		    				 change.setDiscription("leftMemberGraphe "+m.getLogicalName());change.setGraphe(graphe);
		    				 changeGraphe = "remove Node "+m.getLogicalName();
		    				 //Thread.sleep(2000);
		    				 m.setStatus("inactif");
		    				 addMember(m);
		    				 addChangement(change);
		    				}
		    			 }
		    		 }
		    		 for(Node n :listNode ){
		    			 // get member from database
		    			 Member member = findMemberByIdGrapheAndlogicalName(graphe.getId(), n.getLogical_name()) ;
		    			 if( member != null){
		    					// change status
		    				 if(member.getStatus().equals("inactif")){
			    					Changement change = new Changement();
			    					change.setDiscription("changeStatustoActif   "+member.getLogicalName());change.setGraphe(graphe);
			    					listChange.add(change);
			    					//update member
			    					member.setStatus("actif");
			    					addMember(member);
			    					
			    				}
		    				 //search for if there are any change
		    				 // still coordinator !!!
		    				if(!member.getCoordinateur().equals(n.getCoordinateur())){
		    					Changement change = new Changement();
		    					change.setDiscription("changeValueCoordinateurFrom   "+member.getLogicalName()+" "+member.getCoordinateur()+" "+n.getCoordinateur());change.setGraphe(graphe);
		    					addChangement(change);
		    					//update member
		    					member.setCoordinateur(n.getCoordinateur());
		    					addMember(member);
		    				}	
		    				
		    				 // list of view !!!
		    				 ArrayList<String> viewList = n.getView(); ArrayList<String> viewListDb = null;
		    				 if(member.getViewList().length()>0 )
		    				   viewListDb = Lists.newArrayList(member.getViewList().split(" "));	 
		    			//	 System.out.println("------------------view-------------- "+viewList.toString());
		    				  compareTwoList( member, graphe, viewList, viewListDb,0);
		    				//	listChange.addAll(compareTwoList( member, graphe, viewList, viewListDb));
		    				//list of view master 
		    				 ArrayList<String> viewMasterList = n.getViewMaster(); ArrayList<String> viewMasterListDb = null;
		    				 if(member.getViewMasterList().length()>0)
		    					 viewMasterListDb = Lists.newArrayList(member.getViewMasterList().split(" "));
		    			//	 System.out.println("**************master**************** "+viewMasterList.toString());
		    				// if(compareTwoList( member, graphe, viewMasterList, viewMasterListDb).size()>0)
			    			compareTwoList( member, graphe, viewMasterList, viewMasterListDb,1);
		    			 }
		    			 // add new member graphe !!!
		    			 else{ 
		    				 Member memb = HelperStructure.convertNodeToMember(n);memb.setGraphe(graphe);
		    				 addMember(memb);
		    				 Changement change = new Changement();
		    				 change.setDiscription("newMemberInGraphe "+memb.getLogicalName());change.setGraphe(graphe);
		    				 changeGraphe = "new Node "+memb.getLogicalName();
		    				 //Thread.sleep(2000);
		    				 addChangement(change);
		    				// System.out.println("************* addd ***************");
		    			 }
		    		 // fin for !	 
		    	 }
		    	//	 graphe.setListChangement(listChange);
		    		// addGraphe(graphe);
		     }
		     
		}
	}
		public void compareTwoList(Member member,Graphe graphe, ArrayList<String> viewList,ArrayList<String> viewListDb ,int db){
			HashSet<Changement> listChange = new HashSet<Changement>();
			
			if(viewList.size() == 0 && viewListDb == null)
				
			 if(viewList.size()>0 && viewListDb == null){
				 
				 Changement change = new Changement();String views="";
				 for(int i=0;i<viewList.size();i++){
					 if(i == viewList.size() -1 )
						 views+=viewList.get(i);
					 else
						 views+=viewList.get(i)+" ";
				 }
				 change.setGraphe(graphe);
				 
				 if(db == 0){
					 change.setDiscription("newAllMemberView  "+member.getLogicalName()+"  "+views);
					// changeGraphe = "new links between "+member.getLogicalName()+" "+views;
					 member.setViewList(views);
				 }
				
				 else{
					 change.setDiscription("newAllMemberViewMaster  "+member.getLogicalName()+"  "+views);
					 member.setViewMasterList(views);
					 //changeGraphe =" add to list view of "+member.getLogicalName();
				 }
				 addMember(member);
				 addChangement(change);
			 }
			 else if(viewList.size() == 0 && viewListDb != null){
				
				 Changement change = new Changement();
				 change.setGraphe(graphe);
				 
				 if(db == 0){
					 member.setViewList("");
					 change.setDiscription("leftAllMemberView  "+member.getLogicalName()+"  "+member.getViewList());
					 
				 }
				 
				 else{
				    member.setViewMasterList("");
				    change.setDiscription("leftAllMemberViewMaster  "+member.getLogicalName()+"  "+member.getViewList());
				 }
				 addChangement(change);
				 addMember(member);
			 }
				 
			 
			 else{
			//new member in list
				if(viewList != null){
			     for(String view :viewList ){
				      if(!viewListDb.contains(view)){
					      Changement change = new Changement();
    				      change.setGraphe(graphe);
    				      addChangement(change);
    				      if(db == 0){
    				    	  member.setViewList(member.getViewList()+"  "+view);
    				    	  change.setDiscription("newMemberView  "+member.getLogicalName()+"  "+view);
    				    	  //changeGraphe = view+" added to list view of "+member.getLogicalName();
    				      }
    				      else{
    				    	  member.setViewMasterList(member.getViewMasterList()+"  "+view);
    				    	  change.setDiscription("newMemberViewMaster  "+member.getLogicalName()+"  "+view);
    				    	 // changeGraphe = view+" added to list view of "+member.getLogicalName();
    				      }
    				     
				        }
				      }}
			// member left
				if(viewListDb != null){
				  for(String view0 :viewListDb ){
					   if(!viewList.contains(view0)){
						 Changement change = new Changement();
						 if (db == 0)
	    				    {change.setDiscription("leftMemberView  "+member.getLogicalName()+"  "+view0);
	    				    //changeGraphe = view0+" left to list view of "+member.getLogicalName();
	    				    }
						 else
							 {change.setDiscription("leftMemberViewMaster  "+member.getLogicalName()+"  "+view0);
							// changeGraphe = view0+" left to list view of "+member.getLogicalName();
							 }
						 change.setGraphe(graphe);
	    				 addChangement(change);
	    				
   				        // member.setViewList(member.getViewList()+" "+view);
   				         //addMember(member);
					 }}}
				  addMember(member);
			   }
			
		
		}
		@Override
		public List<Member> getAllMemberDB(long idGraphe) {
			
			return Lists.newArrayList(memberRepository.getAllMember(idGraphe));
		}

		@Override
		public List<Changement> getAllChangementByIdGraphe(long idGraphe) {
			return Lists.newArrayList(changementRepository.getAllChangementByIdGraphe(idGraphe));
		}

		@Override
		public List<Changement> getChangementByIdGrapheBetweeDate(
				long idGraphe, Date from, Date to) {
			
			return Lists.newArrayList(changementRepository.findByDateModifBetween(idGraphe, from, to));
		}

		@Override
		public List<Changement> getChangementByIdGrapheBetweeDateHeure(
				long idGraphe, Date date, String from, String to) {
			return Lists.newArrayList(changementRepository.findByheureCreationBetween(idGraphe, date, from, to));
		}

		@Override
		public List<Graphe> getGrapheByDateCreation(Date id) {
			
			return Lists.newArrayList(grapheRepository.findBydateCreationBefore(new Date()));
		}

		@Override
		public Graphe addGraphe(Graphe graphe) {
			return grapheRepository.save(graphe);
		}

		@Override
		public Member addMember(Member member) {
			return memberRepository.save(member);
		}

		@Override
		public Changement addChangement(Changement changement) {
			return changementRepository.save(changement);
		}

		@Override
		public Member findMemberByIdGrapheAndlogicalName(long idgraphe,
				String logicalName) {
			
			return memberRepository.findMemberByIdGrapheAndlogicalName(idgraphe, logicalName);
		}

		@Override
		public List<Member> getAllMemberActif(long idGrpahe) {
			
			return Lists.newArrayList(memberRepository.getAllActifMember(idGrpahe));
		}

		@Override
		public List<Member> getAllMember(long idGrpahe) {
			return Lists.newArrayList(memberRepository.getAllMember(idGrpahe));
		}

		@Override
		public Graphe findBydescription(String description) {
			
			return grapheRepository.findBydescription(description);
		}

		@Override
		public List<Changement> findByDateheureModifBetween(long idGraphe,
				Date datefrom, Date dateTo, String heureFrom, String heureTo) {
			
			return Lists.newArrayList(changementRepository.findByDateheureModifBetween(idGraphe, datefrom, dateTo, heureFrom, heureTo));
		}
		
		@Override
		public GrapheChangement getGrapheAtDateWithAllChange( Date dateFrom, Date dateTo, String heureFrom,String heureTo) {
			ArrayList<Node> listNode = new ArrayList<Node>();
			List<Graphe> listGraphe = getGrapheByDateCreation(dateFrom);
			long idgraphe = listGraphe.get(0).getId();
			List<Member> listMember = getAllMemberDB(idgraphe);
			//System.out.println( "------------------------ "+listMember.size());
			List<Changement> listChangement = findByDateheureModifBetween(idgraphe,dateFrom,dateTo,heureFrom,heureTo);
			ArrayList<Change> listeChangeGraphe = new ArrayList<Change>();
			int i = 0;int index = -1;
			for(Changement change : listChangement){
				String desc = change.getDiscription();i++;
				for(int j =0 ;j<listMember.size();j++)
					if(listMember.get(j).getLogicalName().equals(desc.split(" ")[1].trim()))
					{ index = j; break ;}
				System.out.println("******************* "+index);
				if(change.getDiscription().contains("leftMemberGraphe")){
					listMember.get(index).setStatus("actif");
					listeChangeGraphe.add(new Change("removeNode"+i,listMember.get(index).getLogicalName()));
					continue;
				}
				if(desc.contains("changeStatustoActif")){
					listMember.get(index).setStatus("inactif");
					listeChangeGraphe.add(new Change("addNode"+i,listMember.get(index).getLogicalName()));
					continue;
				}
				if(desc.contains("changeValueCoordinateurFrom")){
					listMember.get(index).setCoordinateur(desc.split(" ")[2]);
					continue;
				}
				if(desc.contains("newMemberInGraphe")){
					listMember.get(index).setStatus("inactif");
					listeChangeGraphe.add(new Change("addNode"+i,listMember.get(index).getLogicalName()));
					continue;
				}
				if(desc.contains("newAllMemberView")){
					listMember.get(index).setViewList("");
					continue;
				}
				if(desc.contains("newAllMemberViewMaster")){
					listMember.get(index).setViewMasterList("");
					continue;
				}
				if(desc.contains("leftAllMemberView")){
					listMember.get(index).setViewList(desc.split(" ")[2]);
					continue;
				}
				if(desc.contains("leftAllMemberViewMaster")){
					listMember.get(index).setViewMasterList(desc.split(" ")[2]);
					continue;
				}
				if(desc.contains("newMemberView")){
					String tmp = listMember.get(index).getViewList();
					tmp.substring(tmp.indexOf(desc.split(" ")[2]), desc.split(" ")[2].length());
					listMember.get(index).setViewList(tmp);
					listeChangeGraphe.add(new Change("addLink"+i,listMember.get(index).getLogicalName()+" "+desc.split(" ")[2]));
					continue;
				}
				if(desc.contains("newMemberViewMaster")){
					String tmp = listMember.get(index).getViewMasterList();
					tmp.substring(tmp.indexOf(desc.split(" ")[2]), desc.split(" ")[2].length());
					listMember.get(index).setViewMasterList(tmp);
					listeChangeGraphe.add(new Change("addLink"+i,listMember.get(index).getLogicalName()+" "+desc.split(" ")[2]));
					continue;
				}
				if(desc.contains("leftMemberView")){
					listMember.get(index).setViewList(listMember.get(index).getViewList()+" "+desc.split(" ")[2]);
					listeChangeGraphe.add(new Change("removeLink"+i,listMember.get(index).getLogicalName()+" "+desc.split(" ")[2]));
					continue;
				}
				if(desc.contains("leftMemberViewMaster")){
					listMember.get(index).setViewMasterList(listMember.get(index).getViewMasterList()+" "+desc.split(" ")[2]);
					listeChangeGraphe.add(new Change("removeLink"+i,listMember.get(index).getLogicalName()+" "+desc.split(" ")[2]));
					continue;
				}
				
			}
			for(Member m :listMember){
			//	System.out.println("------------------- "+m.getStatus());
				if(m.getStatus().equals("actif"))					
				 listNode.add(HelperStructure.convertMemberToNode(m));	
			}	
			GrapheChangement grapheCh = new GrapheChangement();
			grapheCh.setGraphe(createGraph(listNode));
			grapheCh.setListChange(listeChangeGraphe);
			
			
			return grapheCh;
		}

		@Override
		public String getChangeGrapheNotify() {
			
			return changeGraphe;
		}

		@Override
		public RepMethod getListBindAddr() {
			RepMethod rep = new RepMethod();
			ArrayList<Node> list =getAllMembers();
			ArrayList<String>listAd = new ArrayList<String>();
			
			for(Node n:list){
				if(!listAd.contains(n.getBind_addr()))
				  listAd.add(n.getBind_addr());
				rep.getListName().add(n.getLogical_name());
				if(!rep.getListAllAdr().contains(n.getMcast_addr()))
				rep.getListAllAdr().add(n.getMcast_addr());
				
			}
			rep.getListAllAdr().addAll(0, listAd);
			rep.getListName().add("All");
			listAd.add("All");
			rep.setRep(listAd);
			
			return rep;
		}


		
		
		
		

		
		
		
}
