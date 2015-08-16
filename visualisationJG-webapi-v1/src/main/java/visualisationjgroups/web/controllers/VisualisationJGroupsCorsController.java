package visualisationjgroups.web.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import visualisationjgroups.web.models.ApplicationModel;

@Controller
public class VisualisationJGroupsCorsController {
	@Autowired
	private ApplicationModel application;

	// send option to client
	private void sendOptions(HttpServletResponse response) {
		if (application.isCORSneeded()) {
			// fixed header CORS
			response.addHeader("Access-Control-Allow-Origin", "*");
			//  Authorize somr  headers
			response.addHeader("Access-Control-Allow-Headers", "accept,  content-type");
			// authorized POST
			response.addHeader("Access-Control-Allow-Methods", "POST");
		}
	}

	// coordinator db
		@RequestMapping(value ="/getCoord", method = RequestMethod.OPTIONS)
		public void getCoord(HttpServletResponse response) {
			sendOptions(response);
		}
		
	// graph
	@RequestMapping(value = "/getGraph", method = RequestMethod.OPTIONS)
	public void getGraph(HttpServletResponse response) {
		sendOptions(response);
	}
	@RequestMapping(value = "/getAdr", method = RequestMethod.OPTIONS)
	public void getAddress(HttpServletResponse response) {
		sendOptions(response);
	}
	// menu items
	@RequestMapping(value = "/getMenu", method = RequestMethod.OPTIONS)
	public void getMenu(HttpServletResponse response) {
		sendOptions(response);
	}

	// mbeans 
	@RequestMapping(value = "/getMbean/{uuid}/{addr}/", method = RequestMethod.OPTIONS)
	public void getMbeans(HttpServletResponse response) {
		sendOptions(response);
	}
	@RequestMapping(value = "/getMbeanPro/{addr}/", method = RequestMethod.OPTIONS)
	public void getMbeanPro(HttpServletResponse response) {
		sendOptions(response);
	}
    //add protocol
	@RequestMapping(value = "/addProt", method = RequestMethod.OPTIONS)
	public void addProtocol(HttpServletResponse response) {
		sendOptions(response);
	}
    //remove protocol
	@RequestMapping(value = "/removeProt", method = RequestMethod.OPTIONS)
	public void removeProtocol(HttpServletResponse response) {
		sendOptions(response);
	}
}
