package codes.gabor.datapipeline.messages;

import codes.gabor.datapipeline.messaging.RedisMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private RedisMessagePublisher publisher;
    private MessageRepository repository;

    @Autowired
    public MessageService(RedisMessagePublisher publisher, MessageRepository repository) {
        this.publisher = publisher;
        this.repository = repository;
    }

    public void publishMessage(Message message) {
        LOGGER.info("Publishing message...");

        publisher.publish(message.getContent());
    }

    public void consumeMessage(Message message) {
        LOGGER.info("Persisting message...");

        repository.save(message);
    }

    public List<Message> findAll() {
        return repository.findAll();
    }
}
