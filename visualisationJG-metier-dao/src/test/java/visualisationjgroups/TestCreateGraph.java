package visualisationjgroups;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;









import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import visualisationjgroups.config.DomainAndPersistenceConfig;
import visualisationjgroups.domain.Link;
import visualisationjgroups.domain.Node;
import visualisationjgroups.metier.IVisualisationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
public class TestCreateGraph {
	
	@Autowired
	private IVisualisationService service;
	
	@Test
	public void test()
	{
	   System.out.println(" graphe: ");
	   ArrayList <Node> listNode = new ArrayList <Node>();
	   ArrayList <Link> listLink = new ArrayList <Link>();
	   Node node = new Node();
	   node.setLogical_name("groupI:"+0);
	   listNode.add(node);
	   for(int i=1 ;i<50;i++){
		   Node node1 = new Node();
		   node1.setLogical_name("groupI:"+i); listNode.add(node1);
		   Link link = new Link();
		   link.setLink("groupI:"+0);link.setSource(0);link.setTarget(i);
		   listLink.add(link);
	   }/*
	   System.out.println(" size "+listNode.size());
	   Node node2 = new Node();
	   node2.setLogical_name("groupII:"+0);
	   listNode.add(node2);
	   Link link0 = new Link();
	   link0.setLink("groupII:"+0);link0.setSource(50);link0.setTarget(0);
	   listLink.add(link0);
	   for(int i=1 ;i<30;i++){
		   Node node1 = new Node();
		   node1.setLogical_name("groupII:"+i); listNode.add(node1);
		   Link link = new Link();
		   link.setLink("groupII:"+0);link.setSource(50);link.setTarget(50+i);
		   listLink.add(link);
	   }
	   
	   Node node3 = new Node();
	   node3.setLogical_name("groupIII:"+0);
	   listNode.add(node3);
	   Link link1 = new Link();
	   link1.setLink("groupIII:"+0);link1.setSource(80);link1.setTarget(0);
	   listLink.add(link1);
	   for(int i=1 ;i<20;i++){
		   Node node1 = new Node();
		   node1.setLogical_name("groupIII:"+i); listNode.add(node1);
		   Link link = new Link();
		   link.setLink("groupIII:"+0);link.setSource(80);link.setTarget(80+i);
		   listLink.add(link);
	   }
	   
	   Node node4 = new Node();
	   node4.setLogical_name("groupIIII:"+0);
	   listNode.add(node4);
	   
	   
	   for(int i=1 ;i<40;i++){
		   Node node1 = new Node();
		   node1.setLogical_name("groupIIII:"+i); listNode.add(node1);
		   Link link = new Link();
		   link.setLink("groupIIII:"+0);link.setSource(100);link.setTarget(100+i);
		   listLink.add(link);
	   }
	   
	   */
	   Gson gson = new GsonBuilder().create();
	   System.out.println(gson.toJson(listNode));
	   Gson gson1 = new GsonBuilder().create();
	   System.out.println(gson1.toJson(listLink));
	 /*  for(int i=0;i<100;i++){
		   System.out.println(gson.toJson(service.createGraph(service.getAllMembers())));
	   }*/
	//  System.out.println(service.createGraph(service.getAllMembers()));
	 /* Date dateFrom = new Date();
	  System.out.println(service.getGrapheAtDateWithAllChange(dateFrom, dateFrom, "09:00:00", "10:30:00").get("graphe"));
	  TreeMap<String,ArrayList> graphe =(TreeMap<String,ArrayList> )service.getGrapheAtDateWithAllChange(dateFrom, dateFrom, "09:00:00", "10:30:00").get("graphe");
	   Node node =(Node)graphe.get("nodes").get(0);
	   Node node1 =(Node)graphe.get("nodes").get(1);
	  System.out.println("member "+node.getLogical_name()+" v "+node.getView().toString()+" m "+node.getViewMaster().toString());
	  System.out.println("member "+node1.getLogical_name()+" v "+node1.getView().toString()+" m "+node1.getViewMaster().toString());
	  System.out.println("links "+graphe.get("links").toString());
	  */
	   
	}

}
