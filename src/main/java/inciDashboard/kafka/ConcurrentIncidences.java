package inciDashboard.kafka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import inciDashboard.entities.Incidencia;
import inciDashboard.services.IncidenciasService;

@Component
public class ConcurrentIncidences {
	
	@Autowired 
	private IncidenciasService servicioIncidencias;

	public List<Incidencia> incidenciasConcurrentes = new ArrayList<Incidencia>();
	
	public void saveIncidence(Incidencia incidence) {
		servicioIncidencias.addIndicencia(incidence);
		this.incidenciasConcurrentes.add(incidence);		
	}
	
	public List<Incidencia> getIncidenciasConcurrentes(){
		return incidenciasConcurrentes;
	}
}
