package inciDashboard.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Coordenadas {
    @Id
    @GeneratedValue
    private Long id;
    private String latitud;
    private String longitud;

    @OneToOne(mappedBy = "coordenadas", cascade = CascadeType.ALL)
    private Incidencia incidencia;

    public Coordenadas() {

    }

    public Coordenadas(String latitud, String longitud) {
	super();
	this.latitud = latitud;
	this.longitud = longitud;
    }

    public String getLatitud() {
	return latitud;
    }

    public void setLatitud(String latitud) {
	this.latitud = latitud;
    }

    public String getLongitud() {
	return longitud;
    }

    public void setLongitud(String longitud) {
	this.longitud = longitud;
    }

    @Override
    public String toString() {
	return "Coordenadas [id=" + id + ", latitud=" + latitud + ", longitud=" + longitud + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((latitud == null) ? 0 : latitud.hashCode());
	result = prime * result + ((longitud == null) ? 0 : longitud.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Coordenadas other = (Coordenadas) obj;
	if (latitud == null) {
	    if (other.latitud != null)
		return false;
	} else if (!latitud.equals(other.latitud))
	    return false;
	if (longitud == null) {
	    if (other.longitud != null)
		return false;
	} else if (!longitud.equals(other.longitud))
	    return false;
	return true;
    }

}
