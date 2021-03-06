package inciDashboard.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import inciDashboard.entities.User;
import inciDashboard.repositories.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
	List<User> users = new ArrayList<User>();
	usersRepository.findAll().forEach(users::add);
	return users;
    }

    public User getUser(Long id) {
	return usersRepository.findOne(id);
    }

    public void addUser(User user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	usersRepository.save(user);
    }

    public void deleteUser(Long id) {
	usersRepository.delete(id);
    }

    public User getUserByEmail(String email) {
	return usersRepository.findByEmail(email);
    }
}
