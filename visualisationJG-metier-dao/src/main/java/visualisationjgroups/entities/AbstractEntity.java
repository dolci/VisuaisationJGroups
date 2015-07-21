package visualisationjgroups.entities;



import java.io.Serializable;


import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	protected String id;
	@Version
	protected long version;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	// initialisation
	public AbstractEntity build(String id, long version) {
		this.id = id;
		this.version = version;
		return this;
	}

	@Override
	public boolean equals(Object entity) {
		String class1 = this.getClass().getName();
		String class2 = entity.getClass().getName();
		if (!class2.equals(class1)) {
			return false;
		}
		AbstractEntity other = (AbstractEntity) entity;
		return this.id == other.id;
	}

	// getters et setters
	public String getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
