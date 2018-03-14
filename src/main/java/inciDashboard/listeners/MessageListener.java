package inciDashboard.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import inciDashboard.controllers.MainController;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListener.class);

    @KafkaListener(topics = "exampleTopic")
    public void listen(String data) {
        logger.info("New message received: \"" + data + "\"");
    }



}
