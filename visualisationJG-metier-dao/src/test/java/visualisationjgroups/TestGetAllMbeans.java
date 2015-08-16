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
import visualisationjgroups.domain.MbeanShow;
import visualisationjgroups.metier.IVisualisationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
public class TestGetAllMbeans {
	
	@Autowired
	private IVisualisationService service;
	
	@Test
	public void test() throws Exception
	{
	// System.out.println( service.getListBindAddr().getRep().toString());
		
	 Gson gson = new GsonBuilder().create();
	// System.out.println(gson.toJson( service.getAllMBean("", "192.168.1.4"))+"\n\n");
	   System.out.println(gson.toJson(service.invokeMethodProbe("NAKACK2", "printMessages", "All")));
	    	//System.out.println(" ***        "+key+"       ***");
		 // String myCustomArray = gson.toJson(key);
		//  System.out.println(gson.toJson(map)+"\n\n");
		//  service.scheduledHistory();
	    
	}

}
