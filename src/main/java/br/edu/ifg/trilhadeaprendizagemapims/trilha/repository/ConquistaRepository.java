package br.edu.ifg.trilhadeaprendizagemapims.trilha.repository;

import br.edu.ifg.trilhadeaprendizagemapims.trilha.model.EConquista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConquistaRepository extends JpaRepository<EConquista, Long> {
}
