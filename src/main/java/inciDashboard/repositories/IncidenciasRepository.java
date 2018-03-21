package inciDashboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;

public interface IncidenciasRepository extends CrudRepository<Incidencia, Long> {

    List<Incidencia> findAllByUser(User user);

}
