package visualisationjgroups.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import visualisationjgroups.entities.AbstractEntity;

@Entity
@Table(name = "jmxport")
/**
 *  save number of port JMX client
 * @author Aspire
 *
 */
public class JmxPort extends AbstractEntity implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	// jmxport attributes
	@Column
	private String addr;
	@Column
	private long port;
	
	
	//  constructors
	public JmxPort() {
	
	}
	
	public JmxPort(String addr, long port) {
		super();
		this.addr = addr;
		this.port = port;
	}

	@Override
	public String toString() {
		return "JmxPort [uuid=" + addr + ", port=" + port + ", id=" + id
				+ ", version=" + version + "]";
	}
	// getters & setters
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public long getPort() {
		return port;
	}
	public void setPort(long port) {
		this.port = port;
	}
	
	

}
