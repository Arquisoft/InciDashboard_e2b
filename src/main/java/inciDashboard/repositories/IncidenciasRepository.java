package inciDashboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.InciStatus;
import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;

public interface IncidenciasRepository extends CrudRepository<Incidencia, Long> {

    List<Incidencia> findAllByUser(User user);

    @Query("UPDATE Incidencia i SET i.estado = ?1  WHERE i.id = ?2")
    void setStatus(Enum<InciStatus> estado, Long idIncidencia);

}
