package inciDashboard.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.User;
import inciDashboard.services.CommentsService;
import inciDashboard.services.IncidenciasService;
import inciDashboard.services.UsersService;

@Controller
public class UsersController {

    @Autowired
    private IncidenciasService incidenciasService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/user")
    @ResponseBody
    public User user() {
	return new User("Pepe", "pepe@example.com");
    }

    @RequestMapping("/user/listIncidencias")
    public String getListadoIncidencias(Model model, Principal principal) {
	model.addAttribute("incidenciasList",
		incidenciasService.getIncidenciasByUser(usersService.getUserByEmail(principal.getName())));
	return "user/listIncidencias";
    }

    @RequestMapping("/user/listComments/{idIncidencia}")
    public String getListadoComentarios(Model model, Principal principal, @PathVariable Long idIncidencia) {
	model.addAttribute("commentsList",
		commentsService.getCommentsByIncidencia(incidenciasService.getIncidencia(idIncidencia)));
	model.addAttribute("idIncidencia", idIncidencia);
	return "user/listComments";
    }

    @RequestMapping("/user/showMap/{latitud}/{longitud}")
    public String getMap(Model model, @PathVariable double latitud, @PathVariable double longitud) {
	model.addAttribute("latitud", latitud);
	model.addAttribute("longitud", longitud);
	return "coordenadas/map";
    }
    
    @RequestMapping(value = "/user/addComment/{idIncidencia}", method = RequestMethod.POST)
    public String addComment(Model model, @ModelAttribute Comentario comentario, @PathVariable Long idIncidencia) {
	commentsService
		.addComentario(new Comentario(comentario.getTexto(), incidenciasService.getIncidencia(idIncidencia)));
	return "redirect:/user/listComments/{idIncidencia}";
    }
    
    @RequestMapping(value = "/user/addComment/{idIncidencia}")
    public String getPublication(Model model, @PathVariable Long idIncidencia) {
	model.addAttribute("idIncidencia", idIncidencia);
	model.addAttribute("comentario", new Comentario());
	model.addAttribute("commentsList", commentsService.getComentarios());
	return "user/addComment";
    }
    
    
}