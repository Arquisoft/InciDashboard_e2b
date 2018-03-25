package inciDashboard.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Campo {

	@ManyToOne
	private Incidencia incidencia;
	
	private String key;
	private String value;
	
	public Campo(Incidencia incidencia, String key, String value) {
		super();
		this.incidencia = incidencia;
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
