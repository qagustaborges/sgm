package sonarqube.sgm.api.dto;

import java.util.List;
import java.util.Map;

public class ProjetoHistoricoDTO {
    private String projectKey;
    private List<Map<String, Object>> analyses;

    public ProjetoHistoricoDTO() {
        this.projectKey = projectKey;
        this.analyses = analyses;
    }

    // Getters e Setters
}