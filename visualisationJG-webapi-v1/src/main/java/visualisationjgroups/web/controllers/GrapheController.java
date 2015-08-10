package visualisationjgroups.web.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;




import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import visualisationjgroups.web.models.ApplicationModel;
import visualisationjgroups.web.models.Notification;

@Controller
public class GrapheController  {

	@Autowired
	private ApplicationModel application;
	private SimpMessagingTemplate template;
	
	@Autowired
	public GrapheController(SimpMessagingTemplate template){
		this.template = template;
	}
	
	@RequestMapping(value="/jgrousp", method = RequestMethod.GET)
	public  void notifyGraphe() throws Exception{
		System.out.println("000     000   000 "+application.getChangeGrapheNotify());
		Notification notif = new Notification();notif.setMessage( application.getChangeGrapheNotify());
		 Thread.sleep(3000);
		this.template.convertAndSend("/topic/jgrousp", notif);
	}
	/*@MessageMapping("/jgroups")
	@SendTo("/topic/jgroups")
	 public  String notifyGraphe1() throws Exception {
	   Thread.sleep(1000); // simulated delay
		
	        return  ;
	    }*/
}
