package inciDashboard.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private IncidenciasService incidenciasService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("Pedro", "pedro@example.com");
		user1.setPassword("1234");
		User user2 = new User("María", "maria@example.com");
		user2.setPassword("1234");
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		
		Incidencia inci1 = new Incidencia("Incidencia 1", "Temperatura muy alta");
		incidenciasService.addIndicencia(inci1);
	}
}
