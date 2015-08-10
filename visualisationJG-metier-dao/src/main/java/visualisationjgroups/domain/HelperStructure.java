package visualisationjgroups.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import com.google.common.collect.Lists;

import visualisationjgroups.entities.Member;


public class HelperStructure {

	
	public static ArrayList <Node> structureRepMemberProbe(List<String> reponses){
		
		ArrayList <Node> nodes = new ArrayList <Node>();
		ArrayList <SiteMaster> sites = new ArrayList <SiteMaster>();
		TreeMap<String, ArrayList> nodeSite = new TreeMap<String, ArrayList>();
		int nbreMultiRep = 0;
		for(String item : reponses){
		   Node nodei = new Node();
		   String[] items = item.split("\n");
		 
		     if (item.contains("UDP=")) {
		    	  int ind = -1;
		    	  nbreMultiRep++;
		    	  
			   for(int i=0; i < items.length; i++){
				   
			    	if(items[i].contains("local_addr")){
			    		nodei.setLogical_name(items[i].split(" ")[0].substring("local_addr=".length()).trim());
						//nodei.setUuid(items[i].split(" ")[1]);
					     continue;
				       }
				    if(items[i].contains("cluster")){
				    	nodei.setCluster_name(items[i].substring("cluster=".length()).trim());
					 continue;
				     }
				    if(items[i].contains("physical_addr")){
				    	nodei.setPhysical_addr(items[i].substring("physical_addr=".length()).trim());
					 continue;
				    }
				    if(items[i].contains("view")){
				    	
				    	if(nodei.getLogical_name() == null){ind = i;}
				    	else{
				    		   ArrayList<String> view = new ArrayList<String>();
				    	       //System.out.println("************************"+nodei.getLogical_name());
					           view.addAll(Arrays.asList(items[i].split("\\)")[1].substring(2,items[i].split("\\)")[1].length()-2).trim().split(",")));
				    	      if(nodei.getLogical_name().equals(items[i].trim().substring("view=".length()).split("\\|")[0])){ 
				    	    	   nodei.setCoordinateur("true");	
				    	    	   view.remove(0);
							       nodei.setView(view);
				    	      }
						     else{
						       	   //System.out.println("capcity = "+view.capacity());
							       if(view.size() == 1)
								     view = null;
							       else{
							            	int cap = view.size();
							            	//System.out.println("ye  =  "+view.get(1).trim().equals(nodei.getLogical_name()));
							               for(int k=0 ;k<cap;k++)
							            	if(view.get(k).trim().equals(nodei.getLogical_name())){ view.remove(k);nodei.setView(view);break;}
							 
							           }
						          }
						//System.out.println("//////////////////// "+view.get(0));
						    }
						 continue;
					    }
				    if(items[i].contains("UDP")){
				    	String [] udp = items[i].substring("UDP=".length(),items[i].length()-1).trim().split(",");
				    				
						for(int m=0;m<udp.length;m++)
						{
							if(udp[m].contains("external_port")){nodei.setExternal_port(udp[m].trim().substring("external_port=".length()).trim());continue;}
							if(udp[m].contains("bind_port")){nodei.setBind_port(udp[m].trim().substring("bind_port=".length()));continue;}
							if(udp[m].contains("external_addr")){nodei.setExternal_addr(udp[m].trim().substring("external_addr=".length()));continue;}
							if(udp[m].contains("mcast_addr")){ String mcastAddr = udp[m].trim().substring("mcast_addr=/".length());
							    if(mcastAddr.contains("}"))
								nodei.setMcast_addr(mcastAddr.split("}")[0]);continue;}
							if(udp[m].contains("mcast_port")){nodei.setMcast_port(udp[m].trim().substring("mcast_port=".length()));continue;}
							if(udp[m].contains("bind_addr")){ String bindAddr = udp[m];
								if(bindAddr.contains("{"))
								nodei.setBind_addr(udp[m].trim().substring("{bind_addr=/".length()).trim());else
									nodei.setBind_addr(udp[m].trim().substring("bind_addr=/".length()).trim());
								//System.out.println("bidadd************** "+udp[m].substring("{bind_addr=/".length())+"  --- "+udp[m]);continue;
									}
							
						}
							
					 continue;
				    }
				    if(items[i].contains("RELAY2")){
				    	String relay2 = items[i].substring("RELAY2=".length()).trim();
						nodei.setMaster_site(relay2.split(",")[0].substring("site_master= ".length()).trim());
						nodei.setSite(relay2.split(",")[1].substring("site= ".length(),relay2.split(",")[1].length()-1));
						 continue;
					  }
					
			}
			   if(ind != -1)
			   { ArrayList<String> view = new ArrayList<String>();
				  // System.out.println("************************"+nodei.getLogical_name());
			    	if(nodei.getLogical_name().equals(items[ind].substring("view= ".length()).split("\\|")[0]))
						nodei.setCoordinateur("true");	
					view.addAll(Arrays.asList(items[ind].split("\\)")[1].substring(2,items[ind].split("\\)")[1].length()-2).trim().split(",")));
		             
					if(nodei.getCoordinateur().equals("true")){
						view.remove(0);
					    nodei.setView(view);
					}
					else
					{
						//System.out.println("capcity = "+view.capacity());
						if(view.size() > 1){
							int cap = view.size();
							//System.out.println("ye  =  "+view.get(1).trim().equals(nodei.getLogical_name()));
						    for(int k=0 ;k<cap;k++)
						    	if(view.get(k).trim().equals(nodei.getLogical_name()))
						    	 { view.remove(k);nodei.setView(view);break;}
						 
						}
					}
					
			   }
			   nodes.add(nodei);
		     }
		     else{
		    	 //unicast 
		    	 
		    //	 System.out.println("****************"+items+"******************");
		    	 				    	
		    	 String str ="";
		    	
		    	 SiteMaster siteMaster = new SiteMaster();
		    	   for(int i=0; i < items.length; i++){
		    		   
		    		   if(items[i].contains("local_addr")){
				    		str = items[i].split(" ")[0].substring("local_addr=_".length()).trim();	
				    		siteMaster.setNameMember(str.split(":")[0]);
				    		siteMaster.setNameSite(str.split(":")[1]);
				    		//System.out.println("str---------- "+str);
						     continue;
					       }
		    		   if(items[i].contains("view")){
		    			   String [] view = items[i].split("\\)")[1].substring(2,items[i].split("\\)")[1].length()-2).split(",");
		    			    for(String a :view)
		    			    	if(!siteMaster.getNameMember().trim().equals(a.trim().substring(1).split(":")[0]))
		    			    	  siteMaster.getViewMaster().add(a.trim().substring(1).split(":")[0]);
		    			    		//new ArrayList(Arrays.asList(items[i].split("\\)")[1].substring(2,items[i].split("\\)")[1].length()-2).trim().split(",")));
		    			   
		    			   continue;
		    		   }
		    	   }
		    	   sites.add(siteMaster);
		    	  // siteMaster.put(str, view);
		    	 //System.out.println("********* "+ str+" ********* "+);
		     }
		     	
		}
		//
		nodeSite.put("nodes", nodes);
		
		if(sites.size()>0) {
			nodeSite.put("site",sites);
		   return structureListSiteMaster(nodeSite);
		}
		else 
			return nodes;
	}
	public static ArrayList <Node> structureListSiteMaster(TreeMap<String,ArrayList> nodeSite){
		ArrayList <Node> nodes = new ArrayList <Node>();
		ArrayList <SiteMaster> sites = new ArrayList <SiteMaster>();
		nodes = nodeSite.get("nodes") ;
		//System.out.println(" node*********** "+displayList(nodes.get(0).getView()));
		sites = nodeSite.get("site") ;
		//System.out.println(" site*********** "+displayList(sites.get(0).getViewMaster()));
		
		for(SiteMaster s :sites){
			
			for(Node n :nodes){
				
				if(s.getNameSite().trim().equals(n.getSite().trim()) && s.getNameMember().trim().equals(n.getLogical_name().trim())){
					/*System.out.println();
					s.getViewMaster().remove(s.getViewMaster().indexOf(n.getLogical_name().trim()));*/
					n.getViewMaster().addAll(s.getViewMaster());
				}
			}
		}
		return nodes;
	}
	public static String structurePrintStackProtocol(List <String> reponses){
		String res = "";
		for(String item : reponses){
			String[] items = item.split("\n");
			
			if(items[0].contains("UDP"))
				{res = item;break;}
	    }
		return res ;
  }
	public static Set<ObjectInstance> getMbeanForJMXAgent( MBeanServerConnection con)
	{
		 Set<ObjectInstance> tmp =  new HashSet();
		 try {
			  Set<ObjectInstance> mbeans = con.queryMBeans(null, null);
			  for (ObjectInstance objectIns : mbeans)
			  {
				 if(objectIns.getClassName().equals("org.jgroups.JChannel")) 
				 { 
					 tmp.add(objectIns); break;}
			  }
			  for (ObjectInstance objectIns : mbeans)
			  { 
				 if(objectIns.getClassName().contains("org.jgroups.protocols.")) 
					 tmp.add(objectIns);
			  }
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		 return tmp;
	}
	public static int verifMBeanLogiclName(MBeanServerConnection server,String tmp){
		int res = -1;
		Set<ObjectInstance> obj = getMbeanForJMXAgent(server);
		try{
		for (ObjectInstance objectIns : obj)
		  {
			
			if(objectIns.getClassName().equals("org.jgroups.JChannel")) {
				 ObjectName adaptorP = objectIns.getObjectName();
				 MBeanInfo mbi = server.getMBeanInfo(adaptorP);
				 for( MBeanAttributeInfo infAtt: mbi.getAttributes()){
					 
					 if(infAtt.getName().equals("address")){
						 if(server.getAttribute(adaptorP, infAtt.getName()).equals(tmp))
						   res = 1 ;
						return res;
					 }				 	
			  }
			 }
			 
				
		  }
		}
		catch( Throwable t) {
			
			t.printStackTrace();
		}
		return res;
	}
	public static MbeanShow returnAttributeOperationJGroups(MBeanServerConnection server) {
		 //TreeMap<String , Object> attributeOperation = new TreeMap<String , Object>();
		// ArrayList<MBean> listMbeans = new  ArrayList<MBean>();
		 ArrayList<MbeanShow> listMbenShow = new  ArrayList<MbeanShow>();
		 ArrayList<MbeanShow> listMbenShow1 = new  ArrayList<MbeanShow>();
		 MbeanShow sh = new MbeanShow("jgroups");
		 MbeanShow sh1 = new MbeanShow("channel");
		 MbeanShow sh2 = new MbeanShow("protocols");
		 
		 Set<ObjectInstance> objectsIns = getMbeanForJMXAgent(server);
		 int index = 0;
		try{
	    	
		 for(ObjectInstance objIns :objectsIns ){
			//channel
			 MbeanShow mbshow = null;
			 MbeanShow mbshow1 = null;
			 MbeanShow mbshow2 = null;
			   ObjectName adaptorP = objIns.getObjectName();
			  // System.out.println(adaptorP.toString());
			  MBeanInfo mbi = server.getMBeanInfo(adaptorP);
			  if (objIns.getClassName().contains("JChannel")){
				  //attributes
				  mbshow = new MbeanShow("attributes");
				  for( MBeanAttributeInfo infAtt: mbi.getAttributes()){
					  MbeanShow mb = new MbeanShow ("value:"+server.getAttribute(adaptorP, infAtt.getName()));
					  MbeanShow mb0 = new MbeanShow (infAtt.getName());mb0.getChildren().add(mb);
					  mbshow.getChildren().add(mb0);
					 // System.out.println("att  *** "+ mbshow.getChildren().get(0));
				    	// Attribute att = new Attribute(infAtt.getName(),server.getAttribute(adaptorP, infAtt.getName()),infAtt.getDescription());
				    	// attributes.add(att);		    	
				  }
				  //operations
				  mbshow1 = new MbeanShow("operations");
				  for( MBeanOperationInfo infOp: mbi.getOperations()){
					  
					     // paramter operation
					     MBeanParameterInfo[] paramOp = infOp.getSignature();
					     int size = paramOp.length;
					     ArrayList params =new ArrayList();
					     if (size>0){   
					    	         for(int j=0;j<size;j++)
					    	    	  params.add(paramOp[j].getType());
					    	      }
					    	       
				    	 Operation op = new Operation(infOp.getName(),infOp.getReturnType(),infOp.getDescription(),params);
				    	 //System.out.println("signature "+ op.getSignature()+" \n");
				    	 MbeanShow mb = new MbeanShow (op.getSignature());
				    	 MbeanShow mb1 = new MbeanShow (op.getDescription());
						  mbshow1.getChildren().add(mb); mbshow1.getChildren().add(mb1);
				    	// operations.add(op);
				     }
				  sh1.getChildren().add(mbshow);sh1.getChildren().add(mbshow1);
				  
			  }				 				  	  
			  else
			  { 
				  //protocol
				  mbshow =  new MbeanShow(objIns.getClassName().substring("org.jgroups.protocols.".length()));
				  mbshow1 = new MbeanShow("attributes");
				  mbshow2 = new MbeanShow("operations");
				
	        for( MBeanAttributeInfo infAtt: mbi.getAttributes()){
	        	 MbeanShow mb;
	        	if(infAtt.getName().equals("membership_change_policy"))
				  mb = new MbeanShow ("value: ");
	        	else{
	        	  mb = new MbeanShow ("value:"+server.getAttribute(adaptorP, infAtt.getName()));
				  MbeanShow mb0 = new MbeanShow (infAtt.getName());mb0.getChildren().add(mb);
				  mbshow1.getChildren().add(mb0);
				  //System.out.println("att  *** "+ mbshow1.getChildren().get(0));
			  }}
			  //Operations
			  for( MBeanOperationInfo infOp: mbi.getOperations()){
				  
				     // paramter operation
				     MBeanParameterInfo[] paramOp = infOp.getSignature();
				     int size = paramOp.length;
				     ArrayList params =new ArrayList();
				     if (size>0){   
				    	         for(int j=0;j<size;j++)
				    	    	  params.add(paramOp[j].getType());
				    	      }
				    	       
			    	 Operation op = new Operation(infOp.getName(),infOp.getReturnType(),infOp.getDescription(),params);
			    	 //System.out.println("signature "+ op.getSignature()+" \n");
			    	 MbeanShow mb = new MbeanShow (op.getSignature());
			    	 MbeanShow mb1 = new MbeanShow (op.getDescription());
					  mbshow2.getChildren().add(mb);mbshow2.getChildren().add(mb1);
			    	// operations.add(op);
			     }
			  mbshow.getChildren().add(mbshow1);mbshow.getChildren().add(mbshow2);
			  sh2.getChildren().add(mbshow);
		 }
			  }
		
		sh.getChildren().add(sh1);sh.getChildren().add(sh2);
		}
	   catch ( Throwable t) {
			
			t.printStackTrace();
		}
		 return sh;
	}
	
	public static Member convertNodeToMember(Node node){
		 Member member = new Member(); String list="";
		 member.setLogicalName(node.getLogical_name());member.setBind_addr(node.getBind_addr()); member.setPhysical_addr(node.getPhysical_addr());
		 member.setClusterName(node.getCluster_name());member.setMcastAddr(node.getMcast_addr());member.setMcastPort(node.getMcast_port());member.setBind_port(node.getBind_port());
		 member.setSite(node.getSite());member.setCoordinateur(node.getCoordinateur());member.setStatus("actif");member.setExternal_addr(node.getExternal_addr());
		 member.setExternal_port(node.getExternal_port());
		 int size = node.getView().size();
		 if (size == 1)
			 member.setViewList(node.getView().get(0)); 
		 if(size>1){
			 
			 for(int i=0;i<size;i++){
				 if(i == size -1 )
				  list+=node.getView().get(i);
				 else
					 list+=node.getView().get(i)+" ";
			 }				 					 
			 member.setViewList(list);
		 }
		 else member.setViewList("");
		 list ="";
		 size  = node.getViewMaster().size();
		 if (size == 1)
			 member.setViewList(node.getViewMaster().get(0)); 
		 if(size>1){
			 for(int i=0;i<size;i++){
				 if(i == size -1 )
				  list+=node.getViewMaster().get(i);
				 else
					 list+=node.getViewMaster().get(i)+" ";
			 }
		   member.setViewMasterList(list);
		 }
		 else member.setViewMasterList("");
		// member.setColor(node.getColor());
		
		 return member;
	}
	public static Node convertMemberToNode(Member member){
		Node node = new Node();
		 node.setLogical_name(member.getLogicalName());node.setBind_addr(member.getBind_addr()); node.setPhysical_addr(member.getPhysical_addr());
		 node.setCluster_name(member.getClusterName());node.setMcast_addr(member.getMcastAddr());node.setMcast_port(member.getMcastPort());node.setBind_port(member.getBind_port());
		 node.setSite(member.getSite());node.setCoordinateur(member.getCoordinateur());node.setExternal_addr(member.getExternal_addr());
		 node.setExternal_port(node.getExternal_port());
		 node.setView(Lists.newArrayList(member.getViewList().split(" ")));node.setViewMaster(Lists.newArrayList(member.getViewMasterList().split(" ")));
		return node;
	}
	public static String CompareString(String mot1,String mot2){
		
		if(mot1.equals(mot2))
			return null;
		else{
			if(mot1.length()<mot2.length())
				return " add  member ";
				else if(mot1.length()>mot2.length())
					return "member left";
				else{
					 String [] item1 = mot1.split(",");
					 String [] item2 = mot2.split(" ");
					 for(String tmp :item1)
							System.out.println(" *** Probe *** "+tmp);
					 for(String tmp :item2)
						System.out.println(" *** DB *** "+tmp);
					
						 
				}
			
		}
		
			return "";
	}
	public static String displayList(ArrayList<String> a)
	{String sr = "";
		for(String s :a)
			sr+=" --  "+s;
		return sr;
	}
   
	public static int existElemDansList(ArrayList<Menu>list, String item){
		int indice = -1;
		
		for(int i=0;i<list.size();i++){
		//	System.out.println("****1 "+list.get(i).getLabel()+ "******** 2 "+item);
			//System.out.println("*** i "+list.get(i).getLabel().trim() == item);
			if(list.get(i).getLabel().trim().equals(item))
				{indice= i; ;break; }
		}
			
		return indice;
	}

}
