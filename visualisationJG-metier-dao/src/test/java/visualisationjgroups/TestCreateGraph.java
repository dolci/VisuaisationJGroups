package visualisationjgroups;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import visualisationjgroups.config.DomainAndPersistenceConfig;
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
	  System.out.println(service.createGraph(service.getAllMembers()));
	  
	}

}
