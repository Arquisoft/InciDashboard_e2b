package inciDashboard.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Coordenadas;
import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;

@Service
public class InsertSampleDataService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private IncidenciasService incidenciasService;

    @Autowired
    private CoordenadasService coordenadasService;
    
    @PostConstruct
    public void init() {
	User user1 = new User("Pedro", "pedro@example.com");
	user1.setPassword("1234");
	User user2 = new User("María", "maria@example.com");
	user2.setPassword("1234");

	usersService.addUser(user1);
	usersService.addUser(user2);

	Coordenadas coord1 = new Coordenadas("43.3616142", "-5.8506767");
	Coordenadas coord2 = new Coordenadas("43.3579649", "-5.8733862");
	coordenadasService.addCoordenadas(coord1);
	coordenadasService.addCoordenadas(coord2);
	
	Incidencia inci1 = new Incidencia("Sandra", "Incidencia en el bosque", "Temperaturas por encima de 40 grados", coord1, user1);
	Incidencia inci2 = new Incidencia("Juan", "Incidencia en el río", "Río desbordándose", coord2, user2);
	incidenciasService.addIndicencia(inci1);
	incidenciasService.addIndicencia(inci2);
    }
}
