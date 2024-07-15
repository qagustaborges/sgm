package sonarqube.sgm.business.service;

import sonarqube.sgm.api.dto.ProjetoHistoricoDTO;

import java.time.LocalDate;
import java.util.List;

public interface ProjetoHistoricoService {
    List<ProjetoHistoricoDTO> recuperarHistoricoProjetos(String projectName,
                                                         LocalDate startDate,
                                                         LocalDate endDate,
                                                         List<String> metrics) throws Exception;
}
