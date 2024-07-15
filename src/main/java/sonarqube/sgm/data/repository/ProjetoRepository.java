package sonarqube.sgm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sonarqube.sgm.data.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    // Métodos de consulta personalizados, se necessário
}