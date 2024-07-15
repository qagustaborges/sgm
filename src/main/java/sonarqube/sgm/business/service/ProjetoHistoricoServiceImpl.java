package sonarqube.sgm.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sonarqube.sgm.api.dto.ProjetoHistoricoDTO;
import sonarqube.sgm.business.service.ProjetoHistoricoService;
import sonarqube.sgm.data.model.Projeto;
import sonarqube.sgm.data.model.ProjetoHistorico;
import sonarqube.sgm.data.repository.ProjetoRepository;
import sonarqube.sgm.integration.sonarqube.SonarQubeClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoHistoricoServiceImpl implements ProjetoHistoricoService {

    @Autowired
    private SonarQubeClient sonarQubeClient;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public List<ProjetoHistoricoDTO> recuperarHistoricoProjetos() throws Exception {
        List<Projeto> projetos = sonarQubeClient.obterProjetos();
        List<ProjetoHistorico> historicos = projetos.stream()
                .flatMap(projeto -> {
                    try {
                        return sonarQubeClient.obterHistoricoProjeto(projeto).stream();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        // Mapeamento de ProjetoHistorico para ProjetoHistoricoDTO
        return historicos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProjetoHistoricoDTO mapToDTO(ProjetoHistorico projetoHistorico) {
        // Implementação do mapeamento
        ProjetoHistoricoDTO dto = new ProjetoHistoricoDTO();
        // Preencher os campos do DTO com os dados do projetoHistorico
        return dto;
    }
}