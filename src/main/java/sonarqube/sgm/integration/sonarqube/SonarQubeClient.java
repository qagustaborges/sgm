package sonarqube.sgm.integration.sonarqube;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sonarqube.sgm.data.model.Projeto;
import sonarqube.sgm.data.model.ProjetoHistorico;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SonarQubeClient {

    private final String baseUrl;
    private final String token;
    private final HttpClient httpClient;

    public SonarQubeClient(@Value("${sonarqube.base-url}") String baseUrl,
                           @Value("${sonarqube.token}") String token) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String executeGetRequest(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String executePostRequest(String endpoint, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public List<Map<String, Object>> getGates() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/qualitygates/list"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.readValue(response.body(), Map.class);
        return (List<Map<String, Object>>) result.get("qualitygates");
    }

    public List<Projeto> obterProjetos() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/projects/search"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.readValue(response.body(), Map.class);
        return ((List<Map<String, Object>>) result.get("components")).stream()
                .map(this::mapToProjeto)
                .collect(Collectors.toList());
    }

    public List<ProjetoHistorico> obterHistoricoProjeto(Projeto projeto) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/project_analyses/search?project=" + projeto.getKey() + "&branch=master"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.readValue(response.body(), Map.class);
        return ((List<Map<String, Object>>) result.get("analyses")).stream()
                .map(this::mapToProjetoHistorico)
                .collect(Collectors.toList());
    }

    private Projeto mapToProjeto(Map<String, Object> map) {
        Projeto projeto = new Projeto();
        projeto.setKey((String) map.get("key"));
        projeto.setName((String) map.get("name"));
        return projeto;
    }

    private ProjetoHistorico mapToProjetoHistorico(Map<String, Object> map) {
        ProjetoHistorico projetoHistorico = new ProjetoHistorico();
        projetoHistorico.setAnalysisDate((String) map.get("date"));
        return projetoHistorico;
    }
}