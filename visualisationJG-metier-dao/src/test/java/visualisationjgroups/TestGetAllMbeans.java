package visualisationjgroups;

import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;








import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import visualisationjgroups.config.DomainAndPersistenceConfig;
import visualisationjgroups.metier.IVisualisationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
public class TestGetAllMbeans {
	
	@Autowired
	private IVisualisationService service;
	
	@Test
	public void test()
	{
	  
		System.out.println("---- Mbeans  -----");
	    //service.getAllMBean("879244", "localhost");
	    Gson gson = new GsonBuilder().create();
	    TreeMap<String, Object> map = service.getAllMBean("879244", "localhost");
	    for(String key: map.keySet()){
	    	System.out.println(" ***        "+key+"       ***");
		  String myCustomArray = gson.toJson(map.get(key));
		  System.out.println(myCustomArray+"\n\n");
		  service.scheduledHistory();
		}
	}

}
