package sonarqube.sgm.config.sonarqube;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SonarQubeConfig {

    @Value("${sonarqube.base-url}")
    private String url;

    @Value("${sonarqube.token}")
    private String token;

    // Getters e Setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}