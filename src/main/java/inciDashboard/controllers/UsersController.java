package inciDashboard.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.InciStatus;
import inciDashboard.entities.Incidencia;
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

	public List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<SseEmitter>());

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

	@RequestMapping(value = "/user/changeStatus/{idIncidencia}")
	public String setStatus(Model model, @PathVariable Long idIncidencia) {
		model.addAttribute("idIncidencia", idIncidencia);

		Map<String, String> estados = new HashMap<String, String>();
		estados.put("Abierta", InciStatus.ABIERTA.name());
		estados.put("Cerrada", InciStatus.CERRADA.name());
		estados.put("Anulada", InciStatus.ANULADA.name());

		model.addAttribute("estadosList", estados.values());

		return "user/changeStatus";
	}

	@RequestMapping(value = "/user/changeStatus/{idIncidencia}", method = RequestMethod.POST)
	public String getStatus(@PathVariable Long idIncidencia, InciStatus estado) {
		Incidencia original = incidenciasService.getIncidencia(idIncidencia);
		original.setEstado(estado);
		incidenciasService.addIndicencia(original);
		return "redirect:/user/listIncidencias";
	}
	
	@RequestMapping("/emitter")
	public SseEmitter getEmitter() {
		SseEmitter emitter = new SseEmitter(0L);

		emitter.onCompletion(new Runnable() {
			@Override
			public void run() {
				emitters.remove(emitter);
			}
		});
		
		emitter.onTimeout(new Runnable() {
			@Override
			public void run() {
				emitters.remove(emitter);
			}
		});

		emitters.add(emitter);

		return emitter;
	}

}