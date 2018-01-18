package codes.gabor.datapipeline.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity createMessage(@RequestBody String message) {
        LOGGER.info("Request: POST:/api/messages - Add message to queue");

        messageService.publishMessage(message);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        LOGGER.info("Request: GET:/api/messages - Get all messages from the database");

        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }
}
