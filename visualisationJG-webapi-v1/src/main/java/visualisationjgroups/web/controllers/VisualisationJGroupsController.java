package visualisationjgroups.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;














import com.google.common.collect.Lists;

import visualisationjgroups.domain.GrapheChangement;
import visualisationjgroups.domain.MBean;
import visualisationjgroups.domain.MbeanShow;
import visualisationjgroups.domain.RepMethod;
import visualisationjgroups.web.helpers.Static;
import visualisationjgroups.web.models.PostAddProtocol;
import visualisationjgroups.web.models.PostDeleteProtocol;
import visualisationjgroups.web.models.Reponse;
import visualisationjgroups.web.controllers.VisualisationJGroupsCorsController;
import visualisationjgroups.web.models.ApplicationModel;

@RestController
public class VisualisationJGroupsController {
	
	@Autowired
	private ApplicationModel application;
	@Autowired
	private VisualisationJGroupsCorsController visualisationJgroupsCorsController;

	// list of messages
	private List<String> messages;

	@PostConstruct
	public void init() {
		// error messages
		messages = application.getMessages();
	}
	
	
	// graph
		@RequestMapping(value = "/getGraph", method = RequestMethod.GET)
		public Reponse getGraph(HttpServletResponse response) {
			// header CORS
			visualisationJgroupsCorsController.getGraph(response);
			// status application
			if (messages != null) {
				return new Reponse(-1, messages);
			}
			// graph
			try {
				return new Reponse(0, application.createGraph(application.getAllMembers()));
			} catch (Exception e) {
				return new Reponse(1, Static.getErreursForException(e));
			}
		}

		@RequestMapping(value = "/getAdr", method = RequestMethod.GET)
		public Reponse getAddress(HttpServletResponse response) {
			// header CORS
			visualisationJgroupsCorsController.getAddress(response);
			// status application
			if (messages != null) {
				return new Reponse(-1, messages);
			}
			// graph
			try {
				return new Reponse(0, application.getListBindAddr());
			} catch (Exception e) {
				return new Reponse(1, Static.getErreursForException(e));
			}
		}

	// menu
		@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
		public Reponse getMenu(HttpServletResponse response) {
			// header CORS
			visualisationJgroupsCorsController.getMenu(response);
			// status application
			if (messages != null) {
				return new Reponse(-1, messages);
			}
			// menu
			try {
				return new Reponse(0, application.getMenu(application.getAllMembers()));
			} catch (Exception e) {
				return new Reponse(1, Static.getErreursForException(e));
			}
		}

	// mbeans
		@RequestMapping(value = "/getMbean/{uuid}/{addr}/", method = RequestMethod.GET)		
		public Reponse getMbean(@PathVariable("uuid") String uuid, @PathVariable("addr") String addr,HttpServletResponse response) {
			visualisationJgroupsCorsController.getMbeans(response);
			// header application
			if (messages != null) {
				return new Reponse(-1, messages);
			}
				
			  MbeanShow mbeans = null;
			try {
				mbeans = application.getAllMBeanShow(uuid, addr);
				
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			// response
			return new Reponse(0, mbeans);
		}
		
		
		@RequestMapping(value = "/getMbeanPro/{addr}/", method = RequestMethod.GET)		
		public Reponse getMbeanPro(@PathVariable("addr") String addr,HttpServletResponse response) {
			visualisationJgroupsCorsController.getMbeans(response);
			// header application
			if (messages != null) {
				return new Reponse(-1, messages);
			}
				
			ArrayList<MBean> mbeans = null;
			try {
				mbeans = application.getAllMBean("", addr);
				
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			// response
			return new Reponse(0, mbeans);
		}
		
		
		@RequestMapping(value = "/grapheDate/{dateFrom}/{dateTo}/{heureFrom}/{heureTo}/", method = RequestMethod.GET)
		public Reponse getGrapheDate(@PathVariable("dateFrom") String dateFrom,@PathVariable("dateTo") String dateTo,@PathVariable("heureFrom") String heureFrom,@PathVariable("heureTo") String heureTo,HttpServletResponse response) {
			// header CORS
			visualisationJgroupsCorsController.getCoord(response);
			// status application
			
			if (messages != null) {
				return new Reponse(-1, messages);
			}
			// graph
			GrapheChangement rep = null;
			try {
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
				Date date1 = formatter.parse(dateFrom);
				Date date2 = formatter.parse(dateTo);
				rep = application.getGrapheAtDateWithAllChange(date1, date2, heureFrom, heureTo);
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			return new Reponse(0,rep);
				//return new Reponse(0, application.getGrapheAtDateWithAllChange(date1, date2, heureFrom, heureTo));
			
		}
		
	
		@RequestMapping(value = "/addProt", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
		public Reponse addProtocol(@RequestBody PostAddProtocol post, HttpServletResponse response) {
			// entêtes CORS
			visualisationJgroupsCorsController.addProtocol(response);
			// état de l'application
			if (messages != null) {
				return new Reponse(-1, messages);
			}
		
			 String rep = null;
			try {
				rep = application.addProtocol(post.getAddr(), post.getProtocolName(), post.getProtocolTransport(),post.getPosition());
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			// on rend la réponse
			return new Reponse(0,rep);
		}
           
		@RequestMapping(value = "/removeProt", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
		public Reponse supprimerRv(@RequestBody PostDeleteProtocol post, HttpServletResponse response) {
			// header CORS
			visualisationJgroupsCorsController.removeProtocol(response);
			// status application
			if (messages != null) {
				return new Reponse(-1, messages);
			}
			
			String rep = "";
			// suppression protocol
			try {
				rep = application.removeProtocol(post.getAddr(), post.getNameProtocol());
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			// ok
			return new Reponse(0, rep);
		}

		@RequestMapping(value = "/invokeMethod/{protocol}/{nameMethod}/{addr}/", method = RequestMethod.GET)
		public Reponse invokeMethods(@PathVariable("protocol") String nameProtocol, @PathVariable("nameMethod") String nameMethod, @PathVariable("addr") String addr, HttpServletResponse response) {
			 
			  if (messages != null) {
					return new Reponse(-1, messages);
				}	
			  ArrayList<RepMethod> rep = new ArrayList<RepMethod>();
			try {		
				     //System.out.println("addd   *********** "+addr);
					rep = application.invokeMethodProbe(nameProtocol,nameMethod,addr);		
				
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			// ok
			return new Reponse(0, rep);
		}
		@RequestMapping(value = "/setAtt/{addr}/{nameAtt}/{valueAtt}/{protocol}", method = RequestMethod.GET)
		public Reponse setAttribute( @PathVariable("addr") String addr, @PathVariable("nameAtt") String nameAtt,@PathVariable("valueAtt") String valueAtt,@PathVariable("protocol") String nameProtocol, HttpServletResponse response) {
			 
			  if (messages != null) {
					return new Reponse(-1, messages);
				}	
			String rep = "";
			try {		
				     //System.out.println("addd   *********** "+addr);
					rep = application.setAttributeProbe(addr, nameAtt, valueAtt, nameProtocol);		
				
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			// ok
			return new Reponse(0, rep);
		}
       
		@RequestMapping(value = "/getAtt/{addr}/{protocol}/{params}", method = RequestMethod.GET)
		public Reponse readAttribute( @PathVariable("addr") String addr, @PathVariable("protocol") String protocol, @PathVariable("params") String params, HttpServletResponse response) {
			 
			  if (messages != null) {
					return new Reponse(-1, messages);
				}	
			  ArrayList<RepMethod> rep =new ArrayList<RepMethod>();
			try {		
				     //System.out.println("addd   *********** "+addr);
				    		 
				    	
					rep = application.readAttributeProbe(addr, protocol, params);		
				
			} catch (Exception e1) {
				return new Reponse(1, Static.getErreursForException(e1));
			}
			// ok
			return new Reponse(0, rep);
		}


		

}
