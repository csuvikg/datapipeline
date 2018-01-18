package codes.gabor.datapipeline.config;

import codes.gabor.datapipeline.messages.MessageService;
import codes.gabor.datapipeline.messaging.consumer.MessageConsumerThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfig {

    private final ApplicationContext context;

    @Autowired
    public ThreadConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setThreadNamePrefix("executor_thread");
        executor.initialize();

        return executor;
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return args -> executor.execute(new MessageConsumerThread(context.getBean(MessageService.class)));
    }
}
