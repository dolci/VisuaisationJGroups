package visualisationjgroups.boot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import visualisationjgroups.config.DomainAndPersistenceConfig;
import visualisationjgroups.entities.Graphe;
import visualisationjgroups.entities.Member;
import visualisationjgroups.metier.IVisualisationService;

public class Boot {
	
	// le boot
	public static void main(String[] args) {
		
	   
	       /* //AbstractApplicationContext  context0 = new AnnotationConfigApplicationContext(SchedulingHistory.class);
	        SpringApplication app = new SpringApplication(SchedulingHistory.class);
	        ConfigurableApplicationContext context = app.run(args);*/
		//  configuration
		SpringApplication app = new SpringApplication(DomainAndPersistenceConfig.class);
		app.setLogStartupInfo(false);
		//  lance
		ConfigurableApplicationContext context = app.run(args);
		// business layer
		IVisualisationService métier = context.getBean(IVisualisationService.class);
		try {
		/*Graphe grpahe = new Graphe("pou");
		métier.addGraphe(grpahe);
			//métier.findBydescription("pou");
		System.out.println("**** "+ métier.findBydescription("pou").getId());
			Member m =new Member("a", "a", "aa", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", métier.findBydescription("pou"));
			métier.addMember(m);*/
			
			/*Date jour = new Date();
			
			 //   new SimpleDateFormat("dd/MM/yyyy").format(jour)));
		  Coordinateur coordinateur = (Coordinateur) new Coordinateur().build("11L", 1L);coordinateur.setAddr("192.168.1.3");coordinateur.setSite("dddd");
			coordinateur.setCluster("clusetr");coordinateur.setMcastAddr("82");coordinateur.setMcastPort("82");
			Views views =  new Views();views.setVersion(0);views.setListeView("kjhjkhj");views.setType("+");views.setCoordinateur(coordinateur);
			Views views1 = new Views();views1.setVersion(0);views1.setListeView("kjhjkhj");views1.setType("-");views1.setCoordinateur(coordinateur);
			Set  listV = new  HashSet();listV.add(views1);listV.add(views);coordinateur.setListView(listV);
		     métier.addCoordinateur(coordinateur);
		
			display("coordinateur",métier.getAllCoordinateurDB());
			System.out.println("view  "+métier.getViewCoordinateur("11L").get(0).getListeView());
		
			//Views v = métier.addViews(new Views("1L","liste","++",jour,métier.getAllCoordinateur().get(0),1L));
			//System.out.println(v);
			*/
			
			
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getCause());
			
		}
		 //close context Spring
		 
		 
		context.close();
	}

	// 
	private static <T> void display(String message, Iterable<T> elements) {
		System.out.println(message);
		for (T element : elements) {
			System.out.println(element);		
		}
		
	}

}
