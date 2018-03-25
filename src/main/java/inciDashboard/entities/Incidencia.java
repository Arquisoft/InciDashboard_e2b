package inciDashboard.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

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
    
    @OneToMany(mappedBy = "incidencia", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios = new HashSet<Comentario>();
    
    @Temporal(value = TemporalType.DATE)
    private Date caducidad;
    
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "CAMPOS")
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    private Map<String,String> campos = new HashMap<String, String>(); //Resto de valores que pueden variar dependiendo de la incidencia
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean danger = false;

    public Incidencia() {
    }

    public Incidencia(String nombreUsuario, String nombre, String descripcion, Coordenadas coordenadas, Date caducidad, User user, Map<String, String> campos) {
	    super();
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coordenadas = coordenadas;
        this.estado = InciStatus.ABIERTA;
        this.caducidad = caducidad;
        this.user = user;
        this.campos = campos;
        checkDangerousness();
    }

    private void checkDangerousness() {
        for (Map.Entry<String, String> entry : campos.entrySet()) {
            if (entry.getKey().equals("temp")) {
                int temp = Integer.valueOf(entry.getValue()); {
                    if (temp > 40 || temp < 1) {
                        danger = true;
                        break;
                    }
                }
            }
            else if (entry.getKey().equals("wspeed")) {
                int speed = Integer.valueOf(entry.getValue()); {
                    if (speed > 60 || speed < 5) {
                        danger = true;
                        break;
                    }
                }
            }
        }
    }

    public boolean isDanger() {
        return danger;
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

    public Set<Comentario> getComentarios() {
	return new HashSet<Comentario>(comentarios);
    }

    public void setComentarios(Set<Comentario> comentarios) {
	this.comentarios = comentarios;
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
    
    public Map<String, String> getCampos() {
		return new HashMap<String, String>(campos);
	}

	public void setCampos(Map<String, String> campos) {
		this.campos = campos;
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
    	String toString = "Incidencia [id=" + id + ", nombreUsuario=" + nombreUsuario + ", nombre=" + nombre + ", descripcion="
    			+ descripcion + ", latitud =" + coordenadas.getLatitud() + ", longitud =" + coordenadas.getLongitud() + ", estado=" + estado.toString() 
    			+ ", caducidad=" + caducidad.toString();
    	for(Comentario c: comentarios)
    		toString += ", comentario=" + c.getTexto();
    	return toString + " ]";
    }

}
