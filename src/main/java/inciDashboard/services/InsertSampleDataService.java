package inciDashboard.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import inciDashboard.entities.User;

public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("Pedro", "pedro@example.com");
		user1.setPassword("1234");
		User user2 = new User("María", "maria@example.com");
		user2.setPassword("1234");
		
		usersService.addUser(user1);
		usersService.addUser(user2);
	}
}
