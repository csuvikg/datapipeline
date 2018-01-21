package codes.gabor.datapipeline.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private MessageService messageService;
    private SimpMessagingTemplate template;

    @Autowired
    public MessageController(MessageService messageService, SimpMessagingTemplate template) {
        this.messageService = messageService;
        this.template = template;
    }

    @PostMapping
    public ResponseEntity createMessage(@RequestBody @Valid Message message) {
        LOGGER.info("Request: POST:/api/messages - Add message to queue");

        template.convertAndSend("/topic/messages", message.getContent());
        messageService.publishMessage(message);

        return new ResponseEntity(HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        LOGGER.info("Request: GET:/api/messages - Get all messages from the database");

        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }
}
