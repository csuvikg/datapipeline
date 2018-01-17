package codes.gabor.datapipeline.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class RedisMessageSubscriber implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageSubscriber.class);

    public static BlockingQueue<String> messages = new LinkedBlockingDeque<>();

    public void onMessage(Message message, byte[] pattern) {
        LOGGER.info("Adding message <" + message.toString() + "> to queue");
        messages.add(message.toString());
    }
}
