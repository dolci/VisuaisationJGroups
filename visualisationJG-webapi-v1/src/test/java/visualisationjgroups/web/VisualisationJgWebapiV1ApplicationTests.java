package visualisationjgroups.web;

import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import visualisationjgroups.web.boot.Boot;
import visualisationjgroups.web.models.Reponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class VisualisationJgWebapiV1ApplicationTests {

	
	
	@Test
	public void contextLoads() {
		
	//	Reponse mbeans = métier.getAllMBean("service:jmx:rmi:///jndi/rmi://localhost:50000/jmxrmi");
	//	System.out.println("Liste des clients :"+mbeans.getData());
	}
	private void display(String message, Iterable<?> elements) {
		System.out.println(message);
		for (Object element : elements) {
			System.out.println(element);
		}
	}
}
