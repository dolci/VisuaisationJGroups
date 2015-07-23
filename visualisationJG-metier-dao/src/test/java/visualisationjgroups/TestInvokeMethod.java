package visualisationjgroups;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import visualisationjgroups.config.DomainAndPersistenceConfig;
import visualisationjgroups.metier.IVisualisationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
public class TestInvokeMethod {
	
	@Autowired
	private IVisualisationService service;
	
	@Test
	public void test()
	{
	  
	   Boolean erreur = false;
		try {
			// service.invokeMethodProbe("NAKACK2", "printMessages", "192.168.1.5");
			System.out.println("***********************"+service.invokeMethodProbe("NAKACK2", "printMessages", "192.168.1.5"));
		} catch (Exception ex) {
			Throwable th = ex;
			while (th != null) {
				System.out.println(ex.getMessage());
				th = th.getCause();
			}
			// on note l'erreur
			erreur = true;
		}
		// on vérifie qu'il y a eu une erreur
		Assert.assertTrue(erreur);
	}

}
