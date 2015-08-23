package visualisationjgroups;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import visualisationjgroups.config.DomainAndPersistenceConfig;
import visualisationjgroups.metier.IVisualisationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
public class TestPrintAttribute {
	
	@Autowired
	private IVisualisationService service;
	
	@Test
	public void test()
	{
		TreeMap<String,List<String>> prams = new TreeMap<String,List<String>>() ;
		List list1 = new ArrayList();List list2 = new ArrayList();List list3 = new ArrayList();
		list1.add("members");list3.add("diagnostics_ttl");list2.add("num_messages_received");list2.add("num_messages_sent");
		prams.put("GMS", list1);
		prams.put("NAKACK2", list2);
		prams.put("UDP", list3);
		//service.readAttributeProbe("192.168.1.4", prams)
	  // System.out.println(" read attribute : "+service.readAttributeProbe("192.168.1.4", prams));
	}

}
