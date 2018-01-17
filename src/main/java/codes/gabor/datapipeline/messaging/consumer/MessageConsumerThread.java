package codes.gabor.datapipeline.messaging.consumer;

import codes.gabor.datapipeline.messages.Message;
import codes.gabor.datapipeline.messages.MessageService;
import codes.gabor.datapipeline.messaging.RedisMessageSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageConsumerThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerThread.class);

    private MessageService service;

    public MessageConsumerThread(MessageService service) {
        this.service = service;
    }

    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                String message = RedisMessageSubscriber.messages.take();

                if (message != null) {
                    LOGGER.info(Thread.currentThread().getName() + " is consuming message...");

                    service.consumeMessage(new Message(message));
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}