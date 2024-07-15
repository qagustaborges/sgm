package sonarqube.sgm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "sonarqube.sgm.data.repository")
@EntityScan(basePackages = "sonarqube.sgm.data.model")
@ComponentScan(basePackages = "sonarqube.sgm")
public class SonarqubeSgmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SonarqubeSgmApplication.class, args);
    }
}