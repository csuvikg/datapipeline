package codes.gabor.datapipeline.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketController.class);

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public String take(String message) {
        LOGGER.info("Message arrived to WebSocket, publishing to /topic/messages");

        return message;
    }
}
