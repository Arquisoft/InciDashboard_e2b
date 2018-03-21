package inciDashboard.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;
import inciDashboard.repositories.IncidenciasRepository;

@Service
public class IncidenciasService {
    @Autowired
    private IncidenciasRepository incidenciasRepository;

    public List<Incidencia> getIncidencias() {
	List<Incidencia> incidencias = new ArrayList<Incidencia>();
	incidenciasRepository.findAll().forEach(incidencias::add);
	return incidencias;
    }

    public Incidencia getIncidencia(Long id) {
	return incidenciasRepository.findOne(id);
    }

    public void addIndicencia(Incidencia user) {
	incidenciasRepository.save(user);
    }

    public void deleteIncidencia(Long id) {
	incidenciasRepository.delete(id);
    }
    
    public List<Incidencia> getIncidenciasByUser(User user){
	return incidenciasRepository.findAllByUser(user);
    }
}
