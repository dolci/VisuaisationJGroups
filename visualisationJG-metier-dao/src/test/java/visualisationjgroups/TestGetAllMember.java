package visualisationjgroups;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import visualisationjgroups.config.DomainAndPersistenceConfig;
import visualisationjgroups.domain.Node;
import visualisationjgroups.metier.IVisualisationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
public class TestGetAllMember {
	
	@Autowired
	private IVisualisationService service;
	
	@Test
	public void test()
	{
	   System.out.println(" list member : ");
	   for(Node a : service.getAllMembers())
		 a.display();
	   System.out.println(" list coordinator : ");
	   for(Node a : service.getAllCordinateurProbe(service.getAllMembers()))
		 a.display();
	}

}
