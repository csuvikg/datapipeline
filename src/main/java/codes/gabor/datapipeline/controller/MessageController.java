package codes.gabor.datapipeline.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @PostMapping
    public ResponseEntity createMessage(@RequestBody String message) {
        logger.info("Request: POST:/api/messages - Add message to queue");
        logger.info("Message is: " + message);

        System.out.println(message);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<String>> getMessages() {
        logger.info("Request: GET:/api/messages - Get all messages from the database");

        return new ResponseEntity<>(new ArrayList<>(Arrays.asList("Hello", "World")), HttpStatus.OK);
    }
}
