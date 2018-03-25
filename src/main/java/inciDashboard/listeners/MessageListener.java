package inciDashboard.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import inciDashboard.controllers.MainController;
import inciDashboard.controllers.UsersController;

import java.io.IOException;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

    @Autowired
    private UsersController userController;

    @KafkaListener(topics = "incidencia")
    public void listen(String data) {
    	System.out.println(data);

		SseEventBuilder event = SseEmitter.event().name("nuevaIncidencia").data(data);
		synchronized (userController.emitters) {
			for (SseEmitter sseEmitter : this.userController.emitters) {
				try {
					sseEmitter.send(event);
				} catch (IOException e) {
					sseEmitter = new SseEmitter(Long.MAX_VALUE);
				}
			}
		}

	}

}
