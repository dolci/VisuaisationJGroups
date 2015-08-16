package visualisationjgroups.web.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;




import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import visualisationjgroups.web.models.ApplicationModel;
import visualisationjgroups.web.models.Notification;

@Controller
@EnableScheduling
public class GrapheController  {

	@Autowired
	private ApplicationModel application;
	private SimpMessagingTemplate template;
	
	@Autowired
	public GrapheController(SimpMessagingTemplate template){
		this.template = template;
	}
	
 //@RequestMapping(value="/jgrousp", method = RequestMethod.POST)
	/*@MessageMapping("/jgrousp")*/
	@SendTo("/topic/jgrousp")
	//@Scheduled(fixedDelay = 2000)
	public  void notifyGraphe() throws Exception{
		if(application.getChangeGrapheNotify() != null){
		System.out.println("000     000   000 "+application.getChangeGrapheNotify());
		Notification notif = new Notification();notif.setMessage( application.getChangeGrapheNotify());
		 
		this.template.convertAndSend("/topic/jgrousp", application.getChangeGrapheNotify());
		Thread.sleep(3000);
		}
	}
	/*@MessageMapping("/jgroups")
	@SendTo("/topic/jgroups")
	 public  String notifyGraphe1() throws Exception {
	   Thread.sleep(1000); // simulated delay
		
	        return  ;
	    }*/
}
