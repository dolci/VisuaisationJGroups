package visualisationjgroups.web.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




















import com.google.common.collect.Lists;

import visualisationjgroups.web.helpers.Static;
import visualisationjgroups.domain.GrapheChangement;
import visualisationjgroups.domain.GrapheProbe;
import visualisationjgroups.domain.MBean;
import visualisationjgroups.domain.MbeanShow;
import visualisationjgroups.domain.Menu;
import visualisationjgroups.domain.Node;
import visualisationjgroups.domain.RepMethod;
import visualisationjgroups.entities.Changement;
import visualisationjgroups.entities.Graphe;
import visualisationjgroups.entities.JmxPort;
import visualisationjgroups.entities.Member;
import visualisationjgroups.metier.IVisualisationService;


/**
 * Les modèles
 * @author 
 *
 */

@Component
@EnableScheduling
public class ApplicationModel  implements IVisualisationService{

	@Autowired
	private IVisualisationService service;
	
	private GrapheProbe  graph;
	private ArrayList <Node> members;
	private ArrayList<Menu> menu;
	private List<String> messages;
	//private TreeMap<String, Object> mbeans;
	// données de configuration
	private boolean CORSneeded = true;

	/*@PostConstruct
	public void init() {
		
		try {
			members =service.getAllMembers();
			graph = service.createGraph(members);
			menu = service.getMenu(members);
			
		} catch (Exception ex) {
			messages = Static.getErreursForException(ex);
		}
	}*/

	// getter
	public List<String> getMessages() {
		return messages;
	}
	
	public boolean isCORSneeded() {
		return CORSneeded;
	}

	
	

	@Override
	public JmxPort getJmxPortById(String id) {
		
		return null;
	}

	@Override
	public JmxPort addJmxPort(JmxPort jmxPort) {
		
		return null;
	}

	

	@Override
	public ArrayList<Node> getAllMembers() {
		
		return service.getAllMembers();
	}

	@Override
	public GrapheProbe createGraph(ArrayList<Node> members) {
		
		return service.createGraph(members);
	}

	@Override
	public ArrayList<Node> getAllCordinateurProbe(ArrayList<Node> members) {
		
		return null;
	}

	@Override
	public ArrayList<Menu> getMenu(ArrayList<Node> nodess) {
		
		return service.getMenu(nodess);
	}

	@Override
	public ArrayList<RepMethod> invokeMethodProbe(String nameProtocol,
			String nameMethod, String addr) {
		
		return service.invokeMethodProbe(nameProtocol, nameMethod, addr);
	}

	@Override
	public RepMethod readAttributeProbe(String addresseIP,
			TreeMap<String, List<String>> params) {
		
		return service.readAttributeProbe(addresseIP, params);
	}

	@Override
	public String setAttributeProbe(String addresseIP, String params,
			String value, String nameProtocol) {
		
		return service.setAttributeProbe(addresseIP, params, value, nameProtocol);
	}

	@Override
	public String addProtocol(String addr, String name, String transportP) {
		
		return service.addProtocol(addr, name, transportP);
	}

	@Override
	public String removeProtocol(String addr, String name) {
		
		return service.removeProtocol(addr, name);
	}

	@Override
	public String printStackProtocol() {
		
		return null;
	}

	@Override
	  @Scheduled(fixedDelay = 5000)
	public void scheduledHistory() throws Exception {
		service.scheduledHistory();
		
	}

	@Override
	public MbeanShow getAllMBeanShow(String uuid, String addr) {
		
		return service.getAllMBeanShow(uuid, addr);
	}

	@Override
	public List<String> invokeMethodJMX() {
		
		return null;
	}

	@Override
	public List<Member> getAllMemberDB(long idGraphe) {
		
		return service.getAllMemberDB(idGraphe);
	}

	@Override
	public List<Changement> getAllChangementByIdGraphe(long idGrpahe) {
		return service.getAllChangementByIdGraphe(idGrpahe);
	}

	@Override
	public List<Changement> getChangementByIdGrapheBetweeDate(long idGrpahe,
			Date from, Date to) {
		
		return service.getChangementByIdGrapheBetweeDate(idGrpahe, from, to);
	}

	@Override
	public List<Changement> getChangementByIdGrapheBetweeDateHeure(
			long idGrpahe, Date date, String from, String to) {
		
		return service.getChangementByIdGrapheBetweeDateHeure(idGrpahe, date, from, to);
	}

	@Override
	public List<Graphe> getGrapheByDateCreation(Date id) {
		
		return service.getGrapheByDateCreation(id);
	}

	@Override
	public Graphe addGraphe(Graphe grpahe) {
		
		return null;
	}

	@Override
	public Member addMember(Member member) {
		
		return null;
	}

	@Override
	public Changement addChangement(Changement changement) {
		
		return null;
	}

	@Override
	public Member findMemberByIdGrapheAndlogicalName(long idgraphe,
			String logicalName) {
		
		return null;
	}

	@Override
	public List<Member> getAllMemberActif(long idGrpahe) {
		return null;
	}

	@Override
	public List<Member> getAllMember(long idGrpahe) {
		return service.getAllMember(idGrpahe);
	}

	@Override
	public Graphe findBydescription(String description) {
		return null;
	}

	@Override
	public List<Changement> findByDateheureModifBetween(long idGraphe,
			Date datefrom, Date dateTo, String heureFrom, String heureTo) {
		
		return null;
	}

	@Override
	public GrapheChangement getGrapheAtDateWithAllChange(
			Date dateFrom, Date dateTo, String heureFrom, String heureTo) {
		
		return service.getGrapheAtDateWithAllChange(dateFrom, dateTo, heureFrom, heureTo);
	}

	@Override
	public String getChangeGrapheNotify() {
		
		return service.getChangeGrapheNotify();
	}

	@Override
	public ArrayList<MBean> getAllMBean(String logicalName, String addr) {
		
		return service.getAllMBean(logicalName, addr);
	}

	@Override
	public RepMethod getListBindAddr() {
		
		return service.getListBindAddr();
	}

	
	
	
}
