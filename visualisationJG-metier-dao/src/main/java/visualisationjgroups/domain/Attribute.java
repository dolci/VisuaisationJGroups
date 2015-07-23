package visualisationjgroups.domain;

/**
 * un attribut de Mbean
 * 
 *
 */
public class Attribute {
    //nom de l'attribut
	private String name;
	//valeur de l'attribut
	private Object value;
	// description de l'attribut
	private String description;
	
	// --------------constructeur
	
	public Attribute(String name, Object value, String description) {
		super();
		this.name = name;
		this.value = value;
		this.description = description;
	}
	//--------- getters & setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
