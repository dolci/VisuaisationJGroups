package visualisationjgroups.web.models;

public class PostAddProtocol {

	// données du post
	private String protocolName;
	private String protocolTransport;
	private String addr;
	private String position;
	public String getProtocolName() {
		return protocolName;
	}
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
	public String getProtocolTransport() {
		return protocolTransport;
	}
	public void setProtocolTransport(String protocolTransport) {
		this.protocolTransport = protocolTransport;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	// getters et setters
	
}
