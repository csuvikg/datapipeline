package codes.gabor.datapipeline.messages;

import codes.gabor.datapipeline.messaging.RedisMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private RedisMessagePublisher publisher;

    @Autowired
    public MessageService(RedisMessagePublisher publisher) {
        this.publisher = publisher;
    }

    public void consumeMessage(String message) {
        LOGGER.info("Publishing message...");

        publisher.publish(message);
    }
}
