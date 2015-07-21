package visualisationjgroups.web.models;
/**
 * réponse de service web 
 * @author Rabaa
 *
 */
public class Reponse {

	
	// statut de l'opération
	private int status;
	// la réponse JSON
	private Object data;

	// ---------------constructeurs
	public Reponse() {
	}

	public Reponse(int status, Object data) {
		this.status = status;
		this.data = data;
	}

	
	public void incrStatusBy(int increment) {
		status += increment;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
