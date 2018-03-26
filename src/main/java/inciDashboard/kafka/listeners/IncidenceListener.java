package inciDashboard.kafka.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import inciDashboard.controllers.MainController;
import inciDashboard.controllers.UsersController;
import inciDashboard.kafka.producers.util.Topics;

import java.io.IOException;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class IncidenceListener {

    @Autowired
    private UsersController userController;

    @KafkaListener(topics = Topics.NEW_INCIDENCE)
    public void listen(String data) {
    	System.out.println(data);

		SseEventBuilder event = SseEmitter.event().name("nuevaIncidencia").data(data);
		System.out.println(data);
		for (SseEmitter emitter : userController.emitters) {
			try {
				emitter.send(data, MediaType.APPLICATION_JSON);
			} catch (IOException e) {
				emitter.complete();
			}
		}

	}

}
