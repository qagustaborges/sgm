package sonarqube.sgm.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import sonarqube.sgm.integration.sonarqube.SonarQubeClient;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    @Bean
    public SonarQubeClient sonarQubeClient(@Value("${sonarqube.base-url}") String baseUrl,
                                           @Value("${sonarqube.token}") String token) {
        return new SonarQubeClient(baseUrl, token);
    }
}