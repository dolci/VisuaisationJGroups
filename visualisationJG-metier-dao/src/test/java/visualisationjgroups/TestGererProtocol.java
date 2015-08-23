package visualisationjgroups;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import visualisationjgroups.config.DomainAndPersistenceConfig;
import visualisationjgroups.metier.IVisualisationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
public class TestGererProtocol {
	
	@Autowired
	private IVisualisationService service;
	
	@Test
	public void test()
	{
		
	   System.out.println(" list of intial Protocol : "+service.printStackProtocol());
	   service.addProtocol("all", "MERGE3", "UDP","above");
	   System.out.println(" list Protocol  after add : "+service.printStackProtocol());
	   
	   
	   service.removeProtocol("192.168.5.5", "MERGE3");
	   assertEquals("okk",service.removeProtocol("all", "MERGE3"));
	   System.out.println(" list Protocol  after delete : "+service.printStackProtocol());
	}

}
