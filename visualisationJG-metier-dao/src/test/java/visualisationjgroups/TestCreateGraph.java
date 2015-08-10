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
	   Gson gson = new GsonBuilder().create();
	   for(int i=0;i<100;i++){
		   System.out.println(gson.toJson(service.createGraph(service.getAllMembers())));
	   }
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
