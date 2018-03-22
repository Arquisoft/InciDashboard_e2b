package inciDashboard.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Incidencia {
    @Id
    @GeneratedValue
    private Long id;
    private String nombreUsuario;
    private String nombre;
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "coordenadas_id")
    private Coordenadas coordenadas;
    private InciStatus estado;
    private String comentario;
    @Temporal(value = TemporalType.DATE)
    private Date caducidad;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Incidencia() {
    }

    public Incidencia(String nombreUsuario, String nombre, String descripcion, Coordenadas coordenadas, User user) {
	super();
	this.nombreUsuario = nombreUsuario;
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.coordenadas = coordenadas;
	this.estado = InciStatus.ABIERTA;
	this.comentario = "";
	this.caducidad = null;
	this.user = user;
    }

    public String getNombreUsuario() {
	return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Long getId() {
	return id;
    }

    public InciStatus getEstado() {
	return estado;
    }

    public void setEstado(InciStatus estado) {
	this.estado = estado;
    }

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    public Date getCaducidad() {
	return caducidad;
    }

    public void setCaducidad(Date caducidad) {
	this.caducidad = caducidad;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
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
	Incidencia other = (Incidencia) obj;
	if (nombre == null) {
	    if (other.nombre != null)
		return false;
	} else if (!nombre.equals(other.nombre))
	    return false;
	if (nombreUsuario == null) {
	    if (other.nombreUsuario != null)
		return false;
	} else if (!nombreUsuario.equals(other.nombreUsuario))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Incidencia [id=" + id + ", nombreUsuario=" + nombreUsuario + ", nombre=" + nombre + ", descripcion="
		+ descripcion + ", coordenadas=" + coordenadas + ", estado=" + estado + ", comentario=" + comentario
		+ ", caducidad=" + caducidad + "]";
    }

}
