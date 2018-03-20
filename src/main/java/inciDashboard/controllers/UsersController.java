package inciDashboard.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import inciDashboard.entities.User;
import inciDashboard.services.IncidenciasService;

@Controller
public class UsersController {
	
	@Autowired
	private IncidenciasService incidenciasService;
	
	@RequestMapping("/user")
	@ResponseBody
	public User user() {
		return new User("Pepe", "pepe@example.com");
	}

	@RequestMapping("/user/listIncidencias")
	public String getListado(Model model, Principal principal) {
		model.addAttribute("incidenciasList", incidenciasService.getIncidencias());
		return "user/listIncidencias";
	}
}