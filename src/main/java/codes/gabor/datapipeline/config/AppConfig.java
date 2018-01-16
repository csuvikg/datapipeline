package codes.gabor.datapipeline.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"codes.gabor.datapipeline"})
public class AppConfig {
}
