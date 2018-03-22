package inciDashboard.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import inciDashboard.entities.User;
import inciDashboard.services.IncidenciasService;
import inciDashboard.services.UsersService;

@Controller
public class UsersController {

    @Autowired
    private IncidenciasService incidenciasService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/user")
    @ResponseBody
    public User user() {
	return new User("Pepe", "pepe@example.com");
    }

    @RequestMapping("/user/listIncidencias")
    public String getListado(Model model, Principal principal) {
	model.addAttribute("incidenciasList",
		incidenciasService.getIncidenciasByUser(usersService.getUserByEmail(principal.getName())));
	return "user/listIncidencias";
    }
    
    @RequestMapping("/user/showMap/{latitud}/{longitud}")
    public String getMap(Model model, @PathVariable double latitud, @PathVariable double longitud) {
	model.addAttribute("latitud", latitud);
	model.addAttribute("longitud", longitud);
	return "coordenadas/map";
    }
}