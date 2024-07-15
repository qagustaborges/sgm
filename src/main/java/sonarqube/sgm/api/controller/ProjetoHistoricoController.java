package sonarqube.sgm.api.controller;

import sonarqube.sgm.api.dto.ProjetoHistoricoDTO;
import sonarqube.sgm.business.service.ProjetoHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjetoHistoricoController {

    @Autowired
    private ProjetoHistoricoService projetoHistoricoService;

    @GetMapping("/history")
    public List<ProjetoHistoricoDTO> recuperarHistoricoProjetos() throws Exception {
        return projetoHistoricoService.recuperarHistoricoProjetos();
    }
}